package com.test.nettytest.servercontroller;

import com.test.nettytest.util.GpsUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class GPSServerControllerHandler extends ChannelInboundHandlerAdapter
{
	private GPSServerController controller;
	private String uuid;

	public GPSServerControllerHandler(GPSServerController controller, String uuid)
	{
		this.controller = controller;
		this.uuid = uuid;
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		//		System.out.println("连接通道已断开！");
		controller.connect(GpsUtil.HOST, GpsUtil.PORT);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		//连接至服务端后，立即给服务端发送信息，告知服务端，这里是服务端控制台
		//		System.out.println("连接通道已激活！");
		//向服务端发送指令，要取得当前已连接上来的所有客户端
		//ctx.writeAndFlush(Unpooled.wrappedBuffer("ctrl:getClientList".getBytes(CharsetUtil.UTF_8)));		
		ctx.writeAndFlush(Unpooled.copiedBuffer("ctrl:" + uuid, CharsetUtil.UTF_8));
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		//		System.out.println("读取");
		//		ByteBuf in = (ByteBuf) msg;
		//		String msgString = in.readBytes(in.readableBytes()).toString();

		String msgString = (String) msg;

		if (msgString.indexOf("clientList:") > 0) //返回的是客户端列表
		{
			System.out.println("取得客户端列表：");
			System.out.println(msgString);
		}
		else
		{
			System.out.println(msgString);
		}

		ctx.close();
		// 释放资源
		//ReferenceCountUtil.release(in);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}

}
