package com.ewinsh.etone.server.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class WebSocketHandler extends SimpleChannelInboundHandler<String>{
	private final Channel _webSocketChannel;
	
	public WebSocketHandler(Channel webSocketChannel){
		_webSocketChannel = webSocketChannel;
	}
	

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
