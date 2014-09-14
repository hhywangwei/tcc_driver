package com.ewinsh.etone.server.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
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

import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.HeartbeatCommand;

/**
 * 解析接受消息
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月13日
 *
 */
public class CommandDecoder extends MessageToMessageDecoder<ByteBuf>{
	private static final Logger logger = LoggerFactory.getLogger(CommandDecoder.class);
	private static final int HEAD_LEN = 18;
	private static final String HEAD_TAG = "<head>";
	private static final String HEARTBEAT = "<msg>hb</msg>";
	
	private final Channel _webSocketChannel;
	private final Charset _charset;
	private final Commandable _heartbeat;
	private final ByteBuf _buffer;
	
	public CommandDecoder(Channel webSocketChannel){
		_webSocketChannel = webSocketChannel;
		_charset = Charset.forName("GBK");
		_heartbeat = new HeartbeatCommand();
		_buffer = new PooledByteBufAllocator().buffer(20 * 1024);
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
				ctx.channel().writeAndFlush(_heartbeat.build());
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		_buffer.writeBytes(msg);
		readCommand(_buffer, out);
    }
	
	private void readCommand(ByteBuf buffer,List<Object> out){
        int len = buffer.readableBytes();
        while(len > HEAD_LEN){
        	logger.info("Message is {}", buffer.toString(_charset));
        	buffer.markReaderIndex();
        	byte[] h = new byte[HEAD_LEN];
        	buffer.readBytes(h);
        	String head =new String(h, _charset);
        	int mLen = messageLen(head);
        	
        	if(mLen == -1 && len > 0){
        		buffer.skipBytes(1);
        		len = buffer.readableBytes();
        		continue;
        	}
        	
        	if(len < mLen){
        		buffer.resetReaderIndex();
        		break ;
        	}
        	
        	byte[] c = new byte[mLen];
    		buffer.readBytes(c, 0, mLen);
    		String s = new String(c, _charset);
    		len = buffer.readableBytes();
    		
    		if(notHeartbeat(s)){
    			out.add(s);
    		}
        }		
	}
	
	private boolean notHeartbeat(String s){
		return !StringUtils.equals(HEARTBEAT, s);
	}
	
	private int messageLen(String h){
		
		if(!StringUtils.startsWith(h, HEAD_TAG)){
			logger.warn("Message head error, message is {}", h);
			return -1;
		}
		
		try{ 
			String sLen = h.substring(6, 11);
			return Integer.valueOf(sLen);	
		}catch(NumberFormatException e){
			logger.error("Message head not number, message is {}", h);
			return -1;
		}
	}
}
