package com.ewinsh.etone.server.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private static final Logger logger = LoggerFactory.getLogger(LoginSocketFrameHandler.class);
	
	private final ChannelGroup _group;
	
	public LoginSocketFrameHandler(ChannelGroup group){
		_group = group;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
			logger.info("{} start websocket complete.",ctx.channel());
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		_group.writeAndFlush(msg.retain());
		byte[] c = new byte[msg.content().readableBytes()];
		msg.content().readBytes(c);
		
		String m = new String(c);
		logger.debug("websocket in {}", m);
		
		c = new byte[msg.content().readableBytes()];
		msg.content().readBytes(c);
		m = new String(c);
		logger.debug("websocket in2 {}", m);
	}
}
