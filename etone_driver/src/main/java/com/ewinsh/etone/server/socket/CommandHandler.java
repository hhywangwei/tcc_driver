package com.ewinsh.etone.server.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

import com.ewinsh.etone.driver.command.Commandable;

public class CommandHandler extends ChannelOutboundHandlerAdapter {
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if(msg instanceof Commandable){
			Commandable c = (Commandable)msg;
			ByteBuf buf = Unpooled.copiedBuffer(c.build(), CharsetUtil.ISO_8859_1);
			ctx.write(buf, promise);	
		}
		ctx.fireChannelWritabilityChanged();
	}
}
