package com.test.nettytest.client;

import com.test.nettytest.client.channelhandler.LoginHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientInMultipleConnection implements Runnable
{
	public void connect(String host, int port)
	{
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group);
		b.channel(NioSocketChannel.class);
		b.option(ChannelOption.SO_KEEPALIVE, true);
		b.option(ChannelOption.TCP_NODELAY, true);
		b.handler(new ChannelInitializer<SocketChannel>()
		{
			@Override
			protected void initChannel(SocketChannel ch) throws Exception
			{
//				ch.pipeline().addLast(new LoginHandler());
			}
		});
		
		//同时打开多个连接
		for(int i=0;i<NettyClientUtil.PER_THREAD_CONNETIONS;i++)
		{
			b.connect(host, port);
		}
	}
	
	@Override
	public void run()
	{
		connect(NettyClientUtil.SERVER_IP, NettyClientUtil.SERVER_PORT);
	}
}
