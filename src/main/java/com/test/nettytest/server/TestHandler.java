package com.test.nettytest.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestHandler extends ChannelInboundHandlerAdapter
{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
//		ByteBuf in = (ByteBuf) msg;
//		String msgString = ByteBufUtil.hexDump(in.readBytes(in.readableBytes()));
//		System.out.println("接收到如下消息：[" + msgString + "]");
		
		System.out.println("接收到如下消息：[" + msg.toString() + "]");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		ctx.close();
	}

}
