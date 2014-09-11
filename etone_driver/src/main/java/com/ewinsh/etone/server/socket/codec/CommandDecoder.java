package com.ewinsh.etone.server.socket.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandDecoder extends StringDecoder{
	private static final Logger logger = LoggerFactory.getLogger(CommandDecoder.class);
	
	private static final int HEAD_LEN = 18;
	private final Charset charset = Charset.forName("UTF-8");
	
	
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int len = msg.readableBytes();
        if(len > HEAD_LEN){
        	String h =new String(msg.toString(0, HEAD_LEN, charset));
        	int clen = commandLen(h);
        	if(len >= clen){
        		byte[] c = new byte[clen];
        		msg.readBytes(c);
        		String s = new String(c);
        		logger.info("Receive string is {}", s);
        		logger.info("Remain string len is {}", msg.readableBytes());    		
        		out.add(s);
        	}
        }
    }
	
	private int commandLen(String h){
		try{
			String sLen = h.substring(6, 11);
			return Integer.valueOf(sLen);	
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
}
