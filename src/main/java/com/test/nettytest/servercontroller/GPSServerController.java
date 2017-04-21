package com.test.nettytest.servercontroller;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.test.nettytest.util.GPSProtocolMessage;
import com.test.nettytest.util.GpsUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * 模拟调度平台，服务端控制器，其实也是相当于 Netty 的一个客户端，它可以跟其它客户端进行通信。
 */
public class GPSServerController
{
	private Channel channel;
	private Bootstrap bootstrap;

	private String uuid; //一个客户端实例有一个UUID，保证了多个客户端在同一台机子运行时不会混淆

	public void start(String host, int port)
	{
		uuid = UUID.randomUUID().toString();

		EventLoopGroup group = new NioEventLoopGroup();
		try
		{
			bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioSocketChannel.class);
			//bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel ch) throws Exception
				{
					//					ch.pipeline().addLast(new IdleStateHandler())
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new GPSServerControllerHandler(GPSServerController.this, uuid));
				}
			});

			connect(host, port);

			Scanner scanner = new Scanner(System.in);//读取键盘输入
			while (true)
			{
				String line = scanner.nextLine();
				if (line == null || "".equals(line))
				{
					continue;
				}
				//退出字符串
				if ("exit".equals(line))
				{
					channel.close();
					//channel.closeFuture().sync();
					break;
				}
				//给 GPS 终端发送命令
				if (line.indexOf("sendto") > -1)
				{
					String cmd = line.substring(76, 78);
					String data = line.length() > 84 ? line.substring(84) : "";
					try
					{
						StringBuilder sb = new StringBuilder();
						sb.append("ctrl:");
						//sb.append("ctrl:").append(uuid).append(":");
						sb.append("sendto:4253313131313144000000000000000000000000000000000000000000000000;gsp:");
						sb.append((new GPSProtocolMessage(cmd, data)).toString());
						if (channel != null && channel.isActive())
							channel.writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
						System.out.println("向服务端发送了：[" + sb.toString() + "]");
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
					continue;
				}

				//向服务端发送其它自定义命令，例如：getClientList 取得当前已经连接上来的终端机的ID列表
				if (channel != null && channel.isActive())
					channel.writeAndFlush(Unpooled.copiedBuffer("ctrl:" + uuid + ":" + line, CharsetUtil.UTF_8));
			}
			scanner.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			group.shutdownGracefully();
		}
	}

	public void connect(String host, int port)
	{
		if (channel != null && channel.isActive())
		{
			return;
		}

		try
		{
			ChannelFuture future = bootstrap.connect(host, port).sync();			
			future.addListener(new ChannelFutureListener()
			{
				@Override
				public void operationComplete(ChannelFuture future) throws Exception
				{
					if (future.isSuccess())
					{
						channel = future.channel();
						System.out.println("已成功连至服务端：" + host + ":" + port);
						//future.channel().closeFuture().sync();
					}
					else
					{
						System.out.println("连接已断开，3秒后进行重连。");

						future.channel().eventLoop().schedule(new Runnable()
						{
							@Override
							public void run()
							{
								connect(host, port);
							}
						}, 3, TimeUnit.SECONDS);
					}
				}
			});
			//future.channel().closeFuture().sync();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new GPSServerController().start(GpsUtil.HOST, GpsUtil.PORT);
	}

}
