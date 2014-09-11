package com.ewinsh.etone.server.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

import com.ewinsh.etone.server.socket.codec.CommandDecoder;

public class SocketChannelInitializer extends ChannelInitializer<Channel>{
	private final Channel _webSocketChannel;
	
	public SocketChannelInitializer(Channel webSocketChannel){
		_webSocketChannel = webSocketChannel;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new CommandDecoder());
		pipeline.addLast(new CommandHandler());
	}

}
