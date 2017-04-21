package com.udiannet.dispatch.communication.netty.server;

import com.test.nettytest.util.GpsUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GPSClient
{
	public void connect(String host, int port)
	{
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group);
		b.channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<SocketChannel>()
		{

			@Override
			protected void initChannel(SocketChannel ch) throws Exception
			{
				
			}
		});
		
		//同时打开6万个连接，测试并发性
		for(int i=0;i<60000;i++)
		{
			b.connect(host, port);
		}
	}
	
	public static void main(String[] args)
	{
		new GPSClient().connect(GpsUtil.HOST, GpsUtil.PORT);
	}
}
