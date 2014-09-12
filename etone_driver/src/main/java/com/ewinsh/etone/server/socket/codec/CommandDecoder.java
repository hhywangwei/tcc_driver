package com.ewinsh.etone.server.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandDecoder extends  MessageToMessageDecoder<ByteBuf>{
	private static final Logger logger = LoggerFactory.getLogger(CommandDecoder.class);
	private static final int HEAD_LEN = 18;
	private static final String HEART_BEAT = "<head>00013</head><msg>hb</msg>";
	private final Channel _webSocketChannel ;
	private final Charset _charset = Charset.forName("UTF-8");
	
	public CommandDecoder(Channel webSocketChannel){
		_webSocketChannel = webSocketChannel;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				logger.debug("READER_IDLE");
				TextWebSocketFrame frame = new TextWebSocketFrame("{\"code\":1,\"message\":\"Cti server closed\"}");
				_webSocketChannel.writeAndFlush(frame);
				ctx.close();
			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
				logger.debug("WRITER_IDLE");
			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				logger.debug("ALL_IDLE");
				ctx.channel().writeAndFlush(HEART_BEAT);
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int len = msg.readableBytes();
        if(len > HEAD_LEN){
        	String h =new String(msg.toString(0, HEAD_LEN, _charset));
        	int clen = commandLen(h);
        	
        	if(len < clen){
        		return;
        	}
        	
        	byte[] c = new byte[clen];
    		msg.readBytes(c);
    		String s = new String(c);
    		logger.info("Receive string is {},Remain len is {}", s, msg.readableBytes());
    		
    		if(isHeartbeat(s)){
    			return ;
    		}
    		out.add(s);
        }
    }
	
	private boolean isHeartbeat(String s){
		return StringUtils.equals(HEART_BEAT, s);
	}
	
	private int commandLen(String h){
		try{
			String sLen = h.substring(6, 11);
			return Integer.valueOf(sLen) + HEAD_LEN;	
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
}
