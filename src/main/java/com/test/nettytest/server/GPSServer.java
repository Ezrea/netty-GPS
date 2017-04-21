package com.test.nettytest.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.test.nettytest.decoder.GPSStringDecoder;
import com.test.nettytest.kafka.KafkaCachedMonitor;
import com.test.nettytest.util.GpsUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class GPSServer
{
	public void bind(int port)
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try
		{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new LoggingHandler(LogLevel.INFO));
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel ch) throws Exception
				{
					//ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 2, 2, 0));
					ch.pipeline().addLast(new GPSStringFrameDecodeAndCheck(Integer.MAX_VALUE, 4, 2, 2, 0));
					//					ch.pipeline().addLast(new GPSStringDecoder()); 					
					//					ch.pipeline().addLast(new TestHandler());

					ch.pipeline().addLast(new GPSHeartBeatHandler()); //主发心跳，等客户端回应
					ch.pipeline().addLast(new GPSKeyCommandHandler());
					//					ch.pipeline().addLast(new GPSServerHandler()); //处理服务器控制端的指令
				}
			});
			ChannelFuture future = bootstrap.bind(port).sync();
			if (future.isSuccess())
			{
				System.out.println("服务已开启，端口号为：" + port);
			}
			//future.channel().closeFuture().sync();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//bossGroup.shutdownGracefully();
			//workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args)
	{
//		try
//		{
//			System.out.println("1 " + GPSServer.class.getResource("/").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try
//		{
//			System.out.println("2 " + GPSServer.class.getResource("").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try
//		{
//			System.out.println("3 " + GPSServer.class.getClassLoader().getResource("/").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try
//		{
//			System.out.println("4 " + GPSServer.class.getClassLoader().getResource("").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(GpsUtil.getPropertiesValueByKey("netty-port"));
		
//		GpsUtil.getPropertiesValueByKey("netty-port");

		new GPSServer().bind(Integer.parseInt(GpsUtil.getPropertiesValueByKey("netty-port")));

		//new GPSServer().bind(GpsUtil.PORT);
		//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		//		executorService.execute(new KafkaCachedMonitor());
		//		System.out.println("以下将开启 Thrift-Server 服务...");
	}

}
