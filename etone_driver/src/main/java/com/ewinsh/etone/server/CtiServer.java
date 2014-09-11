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

import com.ewinsh.etone.server.websocket.FrameHandler;

public class CtiServer {
	private final static Logger logger = LoggerFactory.getLogger(CtiServer.class);
	
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private final EventLoopGroup workGroup = new NioEventLoopGroup();
	private Channel channel;
	
	public static void main(String[] args){
		logger.info("Start cti server...");
		
		final CtiServer start = new CtiServer();
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
		 String host = "127.0.0.1";
		 int port = 9999;
		 bootstrap.group(bossGroup, workGroup)
		          .channel(NioServerSocketChannel.class)
		          .childHandler(new CtiChannelInitializer(host, port, channelGroup));
//		          .option(ChannelOption.AUTO_READ, false);
		 
		 ChannelFuture future = bootstrap.bind(address);
		 channel = future.channel();
		 return future;
	}
	
	public void destroy() { 
		if (channel != null) {
		    channel.close();
		}
		channelGroup.close();
		bossGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
	}
	
	
	private static class CtiChannelInitializer extends ChannelInitializer<Channel>{
		private final ChannelGroup _group;
		private final String _host;
		private final int _port;
		
		public CtiChannelInitializer(String host, int port,ChannelGroup group){
			_host = host;
			_port = port;
			_group = group;
		}
		
		@Override
		protected void initChannel(Channel ch) throws Exception {
			ChannelPipeline pipeline = ch.pipeline();
			pipeline.addLast(new HttpServerCodec());
			pipeline.addLast(new HttpObjectAggregator(64 * 1024));
			pipeline.addLast(new WebSocketServerProtocolHandler("/cti"));
			pipeline.addLast(new FrameHandler(_host, _port, _group));
		}
	}
}