package com.test.nettytest.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.test.nettytest.util.GPSClientDetailData;
import com.test.nettytest.util.GPSClientMasterDataMap;
import com.test.nettytest.util.GpsUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * 专门处理心跳
 */
public class GPSHeartBeatHandler extends ChannelInboundHandlerAdapter
{
	private volatile ScheduledFuture<?> heartBeat;
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		// channel失效，从Map中移除
		String clientId = GPSClientMasterDataMap.removeBySocketChannel((SocketChannel) ctx.channel());
		if (clientId != null)
		{
			if ("ctrl".equals(clientId))
				System.out.println("[" + df.format(new Date()) + "] 系统检测到控制端失去了连接！");
			else
				System.out.println("[" + df.format(new Date()) + "] 系统检测到ID为 ["
						+ GpsUtil.getRealStringFromHexString(clientId) + "] 的终端刚失去连接！");
		}
		else
		{
			System.out.println("[" + df.format(new Date()) + "] 系统检测到一个通道已失效！");
		}

		// 管道断开后，停止发送心跳机制
		if (heartBeat != null)
		{
			heartBeat.cancel(true);
			heartBeat = null;
			System.out.println("[" + df.format(new Date()) + "] 心跳机制已停止。");
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		//		ByteBuf in = (ByteBuf) msg;
		//		ByteBuf msgBuf = in.readBytes(in.readableBytes());

		//		System.out.println("进入了第 1 通道。");

		String msgString = (String) msg;
		//		System.out.println(msgString);

		//假如是服务器控制端发送过来的命令，则透传
		if (msgString.length() > 0 && "ctrl".equals(msgString.substring(0, 4)))
		{
			//保存服务器控制端信息
			String clientId = msgString.substring(5, 41);
			GPSClientDetailData clientData = new GPSClientDetailData(clientId, (SocketChannel) ctx.channel());
			GPSClientMasterDataMap.addOrReplace(clientId, clientData);
			System.out.println("控制端[" + ctx.channel().remoteAddress() + "]已接入。");
			//透传
			ctx.fireChannelRead(msgString);
			return;
		}

		//String gpsString = ByteBufUtil.hexDump(in.readBytes(in.readableBytes()));
		//String gpsString = ByteBufUtil.hexDump(msgBuf);

		if (msgString.length() > 0)
		{
			//不显示“41”命令字信息
			//			if (!"41".equals(GpsUtil.getCommandString(msgString)))
			//			{

			System.out.println("[" + df.format(new Date()) + "] ["
					+ ((SocketChannel) ctx.channel()).remoteAddress().getPort() + "] 接收到如下消息：[" + msgString + "]");

			//				SocketChannel ctrlChannel = GPSClientMasterDataMap.getSocketChannelByClientId("ctrl");
			//				if (ctrlChannel != null)
			//				{
			//					ctrlChannel.writeAndFlush(Unpooled.copiedBuffer("接收到 GPS 数据：" + msgString, CharsetUtil.UTF_8));
			//				}
			//			}

			String sendMsg = null;
			// 注册登录，并储存已连接上的管道的相关信息
			if ("20".equals(GpsUtil.getCommandString(msgString)))
			{
				// 登录成功,把channel存到服务端的map中
				String clientId = GpsUtil.getClientId(msgString);
				GPSClientDetailData clientData = new GPSClientDetailData(clientId, (SocketChannel) ctx.channel());
				GPSClientMasterDataMap.addOrReplace(clientId, clientData);
				//				System.out.println(GPSClientMasterDataMap.getAllEntry());

				//发送终端机注册登录回应
				sendMsg = "faf50010005d00A0ff020000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001020f";

				ctx.writeAndFlush(Unpooled.copiedBuffer(GpsUtil.hexStringToByteArray(sendMsg)));
				System.out.println("[" + df.format(new Date()) + "] 终端 ["
						+ GpsUtil.getRealStringFromHexString(GpsUtil.getClientId(msgString)) + "] 注册成功！");

				// 一遇到终端机发送的注册登录指令“20”，就为它开启心跳机制。
				heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 5, 5, TimeUnit.SECONDS);

			}
			//终端机的心跳回应消息
			else if ("81".equals(GpsUtil.getCommandString(msgString)))
			{
				System.out.println("[" + df.format(new Date()) + "] 接收到客户端答复的心跳消息：[" + msgString + "]");
			}
			//其它消息透传
			else
			{
				ctx.fireChannelRead(msgString);
			}
		}
		//ctx.close();
		// 释放资源
		//		ReferenceCountUtil.release(in);
		//		ReferenceCountUtil.release(msgBuf);
	}

	/**
	 * 主发心跳任务
	 */
	private class HeartBeatTask implements Runnable
	{
		private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx)
		{
			this.ctx = ctx;
		}

		@Override
		public void run()
		{
			// System.out.println("[" + df.format(new Date()) +
			// "]发送心跳啦。。。。。。。");
			String sendMsg = "faf50010000c0001ff020000000000000000011e";
			try
			{
				ctx.writeAndFlush(Unpooled.copiedBuffer(GpsUtil.hexStringToByteArray(sendMsg)));
				System.out.println("[" + df.format(new Date()) + "] 服务端发送心跳消息给客户端！-->>");
			}
			catch (Exception e)
			{
				System.out.println("[" + df.format(new Date()) + "] 服务端发送心跳消息给客户端失败。");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		if (heartBeat != null)
		{
			heartBeat.cancel(true);
			heartBeat = null;
		}
		cause.printStackTrace();
		ctx.close();
	}
}