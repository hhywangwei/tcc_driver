package com.ewinsh.etone.driver;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CtiStart {
	private final static Logger logger = LoggerFactory.getLogger(CtiStart.class);
	
	public static void main(String[] args){
		CtiStart start = new CtiStart();
		start.start();
		
	}
	
	private void start(){
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.handler(new SimpleChannelInboundHandler<ByteBuf>() {

			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
					throws Exception {
				
				logger.info("Revice data");
				
			}
		});
		bootstrap.group(new NioEventLoopGroup());
		ChannelFuture connFuture = bootstrap.connect("211.136.173.132", 999);
		connFuture.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if(future.isSuccess()){
					logger.info("Connection success");
				}else{
					logger.error("Connection fail");
					future.cause().printStackTrace();
				}
				
			}
			
		});
	}

}
