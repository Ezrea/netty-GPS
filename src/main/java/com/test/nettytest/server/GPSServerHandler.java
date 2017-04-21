package com.test.nettytest.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.test.nettytest.util.GPSClientMasterDataMap;
import com.test.nettytest.util.GpsUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 专门处理服务端控制器命令
 */
public class GPSServerHandler extends ChannelInboundHandlerAdapter
{
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		/******************************************* Thrift 测试 *******************************************/		
//		System.out.println("收到："+(String) msg);
//		Thread.sleep(5000);
//		ctx.writeAndFlush(Unpooled.copiedBuffer("你好。", CharsetUtil.UTF_8));
//		ctx.close();
		/******************************************* Thrift 测试 *******************************************/
		
		//		System.out.println("进入了第 3 通道。");

		String msgString = ((String) msg).toLowerCase();
		//System.out.println("进入了第 3 通道。" + msgString);

		//假如是服务器控制端发送过来的命令，则在这里进行处理
		//if (msg.toString().length() > 0 && "ctrl".equals(msg.toString().substring(0, 4)))
		if (msgString.indexOf("ctrl") > -1)
		{
			//ctx.writeAndFlush(Unpooled.copiedBuffer("你好。", CharsetUtil.UTF_8));
			//控制端发送“getClientList”指令
			//if (msg.toString().length()>5 && "getClientList".equals(msgString.substring(5)))
			if (msgString.indexOf("getclientlist") > -1)
			{
				System.out.println("[" + df.format(new Date()) + "] 接收到控制端发来的指令：[getClientList]");
				sendClientList(ctx);
			}
			//控制端给指定的 GPS 终端发送指令
			else if (msgString.indexOf("sendto") > -1)
			{
				String clientId = msgString.substring(12, 76);
				//System.out.println("clientId: "+clientId);
				SocketChannel gpsChannel = GPSClientMasterDataMap.getSocketChannelByClientId(clientId);
//				System.out.println(gpsChannel);
				if (gpsChannel != null)
				{
					String sendMsg = msgString.substring(81);
					gpsChannel.writeAndFlush(Unpooled.copiedBuffer(GpsUtil.hexStringToByteArray(sendMsg)));
					System.out.println(
							"[" + df.format(new Date()) + "] 控制端给指定的 GPS 终端 [" + clientId + "] 发送了 [" + sendMsg + "]。");
				}
			}
		}
	}

	/**
	 * 根据服务控制端发送过来的“getClientList”指令，给服务控制端返回当前客户端列表
	 */
	private void sendClientList(ChannelHandlerContext ctx)
	{
		List<String> list = GPSClientMasterDataMap.getClientList();
		System.out.println("list：" + list.toString());
		if (list != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("clientList:");
			int i = 0;
			for (String s : list)
			{
				if (!"ctrl".equals(s))
					sb.append(i++ + ": " + s + "; ");
			}

			if (i > 0)
			{
				ctx.writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
				//ctx.writeAndFlush(Unpooled.wrappedBuffer(sb.toString().getBytes(CharsetUtil.UTF_8)));
				System.out.println("即将回复：" + sb.toString());
				//ctx.close();
			}
			else
			{
				ctx.writeAndFlush(Unpooled.copiedBuffer("当前客户端列表为空。", CharsetUtil.UTF_8));
			}
		}
		else
		{
			ctx.writeAndFlush(Unpooled.copiedBuffer("当前客户端列表为空。", CharsetUtil.UTF_8));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}

}
