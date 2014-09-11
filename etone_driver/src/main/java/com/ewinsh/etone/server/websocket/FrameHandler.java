package com.ewinsh.etone.server.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.command.JSONBuilders;
import com.ewinsh.etone.server.socket.SocketChannelInitializer;

public class FrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	private static final Logger logger = LoggerFactory.getLogger(FrameHandler.class);
	
	private final String _host;
	private final int _port;
	private final ChannelGroup _group;
	private volatile Channel _ctiChannel;
	
	public FrameHandler(String host, int port, ChannelGroup group){
		_host = host;
		_port = port;
		_group = group;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
 		if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
			logger.info("{} start websocket server.",ctx.channel());
		}
		super.userEventTriggered(ctx, evt);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx)throws Exception{
		final Channel inChannel = ctx.channel();
		
		Bootstrap boot =new Bootstrap();
		boot.group(ctx.channel().eventLoop()).
		     channel(ctx.channel().getClass()).
		     handler(new SocketChannelInitializer(inChannel)).
		     option(ChannelOption.SO_KEEPALIVE,true).
		     option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000);
	    ChannelFuture future = boot.connect(_host, _port);
	    future.addListener(new ChannelFutureListener() {
	    	@Override
            public void operationComplete(ChannelFuture future) {
    		    if (future.isSuccess()) {
    		    	logger.info("Connection cti server success......");
   	            }else{
   	            	logger.error("Connection cti server fail, error is {}",  future.cause().getMessage());
   	            	TextWebSocketFrame frame = new TextWebSocketFrame("{\"code\":100,\"message\":\"Connection cti server fail\"}");
   	            	inChannel.writeAndFlush(frame);
   	            	inChannel.close();
   	            }
   	        }
   	    });
	    _ctiChannel = future.channel();
	    super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		String c = msg.text();
		logger.debug("Receive command is {}", c);
		try{
			if(_ctiChannel.isActive()){
				Commandable command = JSONBuilders.build(c);
				_ctiChannel.writeAndFlush(command);	
			}else{
				TextWebSocketFrame frame = new TextWebSocketFrame("{\"code\":100,\"message\":\"Connection cti server fail\"}");
				ctx.channel().writeAndFlush(frame);
			}
		}catch(Exception e){
			logger.error("Parse \"{}\" command error is {}", c, e.getMessage());
			if(_ctiChannel.isActive()){
				TextWebSocketFrame frame = new TextWebSocketFrame("{\"code\":101,\"message\":\"JSON is error\"}");
				ctx.channel().writeAndFlush(frame);
			}
		}
	}
	
	 @Override
	 public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		 logger.debug("channel Inactive....");
		 _group.remove(ctx.channel());
		 _ctiChannel.close();
         super.channelInactive(ctx);
	 }
	
	 @Override
	 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		 logger.error(cause.getMessage());
	 }
}
