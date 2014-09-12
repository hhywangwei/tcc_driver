package com.ewinsh.etone.server.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import com.ewinsh.etone.server.socket.codec.CommandDecoder;

public class SocketChannelInitializer extends ChannelInitializer<Channel>{
	private final Channel _webSocketChannel;
	
	public SocketChannelInitializer(Channel webSocketChannel){
		_webSocketChannel = webSocketChannel;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new IdleStateHandler(60, 0, 20,TimeUnit.SECONDS));
		pipeline.addLast(new CommandDecoder(_webSocketChannel));
		pipeline.addLast(new WebSocketHandler(_webSocketChannel));
		pipeline.addLast(new CommandHandler());
	}

}
