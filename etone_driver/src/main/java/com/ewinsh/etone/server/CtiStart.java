package com.ewinsh.etone.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ewinsh.etone.server.websocket.LoginSocketFrameHandler;

public class CtiStart {
	private final static Logger logger = LoggerFactory.getLogger(CtiStart.class);
	
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group1 = new NioEventLoopGroup();
	private final EventLoopGroup group2 = new NioEventLoopGroup();
	private Channel channel;
	
	public static void main(String[] args){
		logger.info("Start cti server...");
		
		final CtiStart start = new CtiStart();
		ChannelFuture future = start.start(new InetSocketAddress(8080));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.debug("Stop cti server...");
			    start.destroy();
			}
		});
		future.channel().closeFuture().syncUninterruptibly();
	}
	
	public ChannelFuture start(InetSocketAddress address){
		 ServerBootstrap  bootstrap = new ServerBootstrap();
		 bootstrap.group(group1, group2)
		          .channel(NioServerSocketChannel.class)
		          .childHandler(new CtiChannelInitializer(channelGroup));
		 
		 ChannelFuture future = bootstrap.bind(address);
		 future.syncUninterruptibly();
		 channel = future.channel();
		 return future;
		 
	}
	
	public void destroy() { 
		if (channel != null) {
		    channel.close();
		}
		channelGroup.close();
		group1.shutdownGracefully();
		group2.shutdownGracefully();
	}
	
	
	static class CtiChannelInitializer extends ChannelInitializer<Channel>{
		private final ChannelGroup _group;
		
		public CtiChannelInitializer(ChannelGroup group){
			_group = group;
		}
	
		
		@Override
		protected void initChannel(Channel ch) throws Exception {
			ChannelPipeline pipeline = ch.pipeline();
			pipeline.addLast(new HttpServerCodec());
			pipeline.addLast(new HttpObjectAggregator(64 * 1024));
			pipeline.addLast(new WebSocketServerProtocolHandler("/cti"));
			pipeline.addLast(new LoginSocketFrameHandler(_group));
		}
		
	}
}
