package com.udiannet.dispatch.communication.netty.server;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.test.nettytest.util.GPSProtocolMessage;
import com.test.nettytest.util.GpsUtil;
import com.test.nettytest.util.GpsUtil.CommonGPSDataName;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 根据协议，要针对关键数据进行回复
 */
public class GPSKeyCommandHandler extends ChannelInboundHandlerAdapter
{
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		//		System.out.println("进入了第 2 通道。");

		//处理透传过来的消息，都已经转换为 String 了
		String msgString = (String) msg;

		//假如是服务器控制端发送过来的命令，则透传
		if (msgString.length() > 0 && "ctrl".equals(msgString.substring(0, 4)))
		{
			ctx.fireChannelRead(msg);
			return;
		}

		String sendMsg = null;

		List<String> list = Arrays.asList(GpsUtil.KEYCOMMAND);
		String command = GpsUtil.getCommandString(msgString);
		if (list.indexOf(command) != -1)
		{
			StringBuilder sb = new StringBuilder();
			// 关键数据应答的通用字段
			sb.append("0010001400c1ff020000000000000000");
			// 命令字
			sb.append(command);
			// 获取包序号
			sb.append(GpsUtil.getPackageIndex(msgString));
			// 计算应答时间
			sb.append(GpsUtil.getDistanceSecond());
			// 增加保留值
			sb.append("0000");
			// 计算校验值
			sb.append(GpsUtil.getCheckString(sb.toString()));
			// 校验完后增加起始标示
			sb.insert(0, "faf5");

			sendMsg = sb.toString();

			System.out.println("[" + df.format(new Date()) + "] 根据关键 [" + command + "] 指令，以及包序号["
					+ GpsUtil.getPackageIndex(msgString) + "]，向终端发送了：[" + sendMsg + "]");

			ctx.writeAndFlush(Unpooled.copiedBuffer(GpsUtil.hexStringToByteArray(sendMsg)));

			//根据协议，需要对关键数据里的“46”（请求及报告信息发送）指令作额外的应答
			if ("46".equals(command) && msgString.length() == 184)
				requestInfoHandler(ctx, msgString);
		}
		//ctx.close();
	}

	/**
	 * 根据协议，需要对关键数据里的“46”（请求及报告信息发送）指令作额外的应答
	 */
	private void requestInfoHandler(ChannelHandlerContext ctx, String msg)
	{
		StringBuilder sb = new StringBuilder();
		//城市区号
		sb.append(GpsUtil.getCommonGPSData(msg, CommonGPSDataName.CITYCODE));
		//System.out.println("CITYCODE: " + GpsUtil.getCommonGPSData(msg, CommonGPSDataName.CITYCODE));
		//车辆编号
		sb.append(GpsUtil.getCommonGPSData(msg, CommonGPSDataName.BUSCODE));
		//System.out.println("BUSCODE: " + GpsUtil.getCommonGPSData(msg, CommonGPSDataName.BUSCODE));
		//线路号
		sb.append(GpsUtil.getCommonGPSData(msg, CommonGPSDataName.LINECODE));
		//System.out.println("LINECODE: " + GpsUtil.getCommonGPSData(msg, CommonGPSDataName.LINECODE));

		//请求消息代码
		sb.append(msg.substring(176, 180));
		//请求消息应答
		sb.append("06");

		try
		{
			//按协议将信息打包
			String sendMsg = new GPSProtocolMessage("c6", sb.toString()).toString();
			//给车载终端发送回应信息
			ctx.writeAndFlush(Unpooled.copiedBuffer(GpsUtil.hexStringToByteArray(sendMsg)));

			System.out.println("[" + df.format(new Date()) + "] 根据关键 [46] 指令，向终端发送了：[" + sendMsg + "]");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		//System.out.println(sb.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}

}
