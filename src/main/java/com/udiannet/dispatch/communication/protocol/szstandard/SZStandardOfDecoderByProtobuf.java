package com.udiannet.dispatch.communication.protocol.szstandard;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.protobuf.ByteString;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.CmdType;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.CommonGps;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.OperatingDetailType;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.SZStandardProtocol;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.SourcePackageTopic;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.tRegisterReq;

public class SZStandardOfDecoderByProtobuf
{
	/**
	 * 把接收到的包按协议组成格式进行分解
	 */
	public static SZStandardProtocol decoderByProtocol(byte[] gpsOriginalByteArray)
	{
		SZStandardProtocol.Builder builder = SZStandardProtocol.newBuilder();
		builder.setVersion("1");
		builder.setProtocolVersion(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 2, 2).toByteArray()));//版本号
		
		builder.setMessageLength(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 4, 2).toByteArray()),
				16)); //报文长度

		builder.setSendingSerialNum(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 6, 1).toByteArray()), 16)); //发送序列号

		builder.setCmdType(CmdType.forNumber(ByteString.copyFrom(gpsOriginalByteArray, 7, 1).byteAt(0))); //命令字
		
		builder.setReceiverType(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 8, 1).toByteArray())); //接收方类型
		
		builder.setSenderType(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 9, 1).toByteArray()));//发送方类型

		builder.setReceiverAddress(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 10, 4).toByteArray())); //接收方地址
		
		builder.setSenderAddress(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 14, 4).toByteArray())); //发送方地址
		
		builder.setGspData(ByteString.copyFrom(gpsOriginalByteArray, 18, gpsOriginalByteArray.length - 20));//数据内容
		return builder.build();
	}

	/**
	 * 解析 70 字节的基本数据
	 */
	public static CommonGps getCommonGpsInstance(byte[] gpsBasicDataByteArray)
	{
		CommonGps.Builder builder = CommonGps.newBuilder();
		builder.setVersion("1");
		builder.setCityCode(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 0, 3).toByteArray())); // 1 城市区号 BCD	

		builder.setIndustryCode(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 3, 1).toByteArray())));// 2 行业代码 HEX

		builder.setOrgCode(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 4, 1).toByteArray())));// 3 企业代码 HEX	

		builder.setBusId(ByteString.copyFrom(
				SZStandardUtil.removeZeroFromLast(ByteString.copyFrom(gpsBasicDataByteArray, 5, 8).toByteArray())));// 4 车辆编码 ASCII

		builder.setLineId(ByteString.copyFrom(
				SZStandardUtil.removeZeroFromLast(ByteString.copyFrom(gpsBasicDataByteArray, 13, 8).toByteArray())));// 5 线路号 ASCII

		builder.setParkOrder(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 21, 1).toByteArray())));// 6 停车场序号 HEX

		builder.setParkId(ByteString.copyFrom(SZStandardUtil
				.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 22, 8).toByteArray()).getBytes()));// 7 停车场编号 BCD

		builder.setStopOrder(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 30, 1).toByteArray())));// 8 站点序号 HEX		

		builder.setStopId(ByteString.copyFrom(SZStandardUtil
				.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 31, 8).toByteArray()).getBytes()));// 9 站点编号 BCD

		builder.setLng(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 39, 2).toByteArray())) + Float
						.parseFloat(SZStandardUtil.bcdToDecimal(
								ByteString.copyFrom(gpsBasicDataByteArray, 41, 1).toByteArray()) + "."
						+ SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 42, 2).toByteArray()))
						/ 60);// 10 车辆位置经度 BCD 0121°XX.xxxx′

		builder.setLat(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 44, 1).toByteArray())) + Float
						.parseFloat(SZStandardUtil.bcdToDecimal(
								ByteString.copyFrom(gpsBasicDataByteArray, 45, 1).toByteArray()) + "."
						+ SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 46, 2).toByteArray()))
						/ 60);// 11 车辆位置纬度 BCD 31°XX.xxxx′

		try
		{
			builder.setGpsTimestamp(new SimpleDateFormat("yyMMddHHmmss")
					.parse( // 12 日期、时间 BCD
							String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 48, 1).toByteArray()))) //年
							+ String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 49, 1).toByteArray()))) //月
							+ String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 50, 1).toByteArray()))) //日
							+ String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 51, 1).toByteArray()))) //时
							+ String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 52, 1).toByteArray()))) //分
							+ String.format("%02d",
									Integer.parseInt(SZStandardUtil.bcdToDecimal(
											ByteString.copyFrom(gpsBasicDataByteArray, 53, 1).toByteArray())))) //秒
					.getTime());
		}
		catch (ParseException e)
		{
			builder.setGpsTimestamp(0);
		}

		builder.setSensorSpeed(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 54, 2).toByteArray())) / 10);// 13 速度（传感器） BCD 2字节 xxx.x km/h

		builder.setGpsSpeed(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 56, 2).toByteArray())) / 10);// 14 速度（GPS） BCD 2字节 xxx.x km/h

		builder.setAngle(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 58, 2).toByteArray())) / 10);// 15 走向 BCD 2字节 000.0～359.9°

		builder.setMileage(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsBasicDataByteArray, 66, 4).toByteArray())) / 100);// 18 累计里程 BCD xx xx xx.xx km

		builder.setIsGpsVaild(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(0))); //17 运营状态 第1字节 GPS是否有效

		builder.setIsInPark(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(1))); //17 运营状态 第1字节  停车场内外
		builder.setIsInStartEnd(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(2))); //17 运营状态 第1字节 起终点内外
		builder.setIsInLineUp(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(3))); //17 运营状态 第1字节 上下行
		builder.setIsDoorOpen(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(4))); //17 运营状态 第1字节 开关门
		builder.setIsArriveStop(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(5))); //17 运营状态 第1字节 到离站
		builder.setIsOperating(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(6))); //17 运营状态 第1字节 是否营运
		builder.setIsInLine(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 62, 1).toByteArray()))
				.charAt(7))); //17 运营状态 第1字节 是否在线路上

		builder.setIsDiskGood(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 63, 1).toByteArray()))
				.charAt(3))); //17 运营状态 第2字节 硬盘是否正常
		builder.setIsSDGood(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 63, 1).toByteArray()))
				.charAt(4))); //17 运营状态 第2字节 SD卡是否正常
		builder.setIsInNormalMode(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 63, 1).toByteArray()))
				.charAt(5))); //17 运营状态 第2字节 是否省电模式
		builder.setIsOverSpeed(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 63, 1).toByteArray()))
				.charAt(6))); //17 运营状态 第2字节 是否超速
		builder.setIsLinkDown(SZStandardUtil.intToBoolean(SZStandardUtil
				.hexStringToBinaryString(SZStandardUtil
						.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 63, 1).toByteArray()))
				.charAt(7))); //17 运营状态 第2字节 链路是否正常

		builder.setOperatingDetail(OperatingDetailType.forNumber(Integer.parseInt(
				SZStandardUtil.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 64, 1).toByteArray()),
				16)));//17 运营状态 第3字节

		builder.setVideoChannelStatus(ByteString.copyFrom(SZStandardUtil
				.byteArrayToHexString(ByteString.copyFrom(gpsBasicDataByteArray, 65, 1).toByteArray()).getBytes())); //17 运营状态 第4字节

		return builder.build();
	}

	/**
	 * 终端注册，命令字0x20（TCP连接时终端主发）
	 */
	public static tRegisterReq getTRegisterReq(byte[] gpsDataByteArray)
	{
		tRegisterReq.Builder builder = tRegisterReq.newBuilder();
		builder.setVersion("1");
		builder.setCommonGps(getCommonGpsInstance(ByteString.copyFrom(gpsDataByteArray, 0, 70).toByteArray())); //基本数据  70 字节

		builder.setTerminalSoftwareVersion(ByteString.copyFrom(
				SZStandardUtil.removeZeroFromLast(ByteString.copyFrom(gpsDataByteArray, 70, 32).toByteArray()))); //终端程序版本号 32 字节 （ASCII）

		builder.setTerminalSerial(ByteString.copyFrom(
				SZStandardUtil.removeZeroFromLast(ByteString.copyFrom(gpsDataByteArray, 102, 32).toByteArray())));//终端序列号 32 字节 (ASCII)

		builder.setHeartbeatInteral(Integer
				.parseInt(SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsDataByteArray, 134, 2).toByteArray())));//心跳时间 2 字节 （BCD xxxx s）

		builder.setHeartbeatDistance(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsDataByteArray, 136, 2).toByteArray())) / 100); //发送间隔里程 2 字节 （BCD xx.xx km）

		builder.setLimitSpeed(Float.parseFloat(
				SZStandardUtil.bcdToDecimal(ByteString.copyFrom(gpsDataByteArray, 138, 2).toByteArray())) / 10); //全程限速值 2 字节 （BCD xxx.x km/h）

		builder.setIMSI(ByteString.copyFrom(
				SZStandardUtil.removeZeroFromLast(ByteString.copyFrom(gpsDataByteArray, 140, 15).toByteArray())));//IMSI卡号 15 字节 (ASCII)

		builder.setReceiveTime(System.currentTimeMillis()); //接收时间

		return builder.build();
	}

	/**
	 * 原始包
	 */
	public static SourcePackageTopic getSourcePackageTopic(byte[] gpsDataByteArray)
	{
		SourcePackageTopic.Builder builder = SourcePackageTopic.newBuilder();
		builder.setVersion("1");
		builder.setCmpType(CmdType.forNumber(ByteString.copyFrom(gpsDataByteArray, 7, 1).byteAt(0)));
		builder.setProcessingTime(System.currentTimeMillis());
		CommonGps commonGps = getCommonGpsInstance(
				ByteString.copyFrom(gpsDataByteArray, 18, gpsDataByteArray.length - 20).toByteArray());
		builder.setLineId(commonGps.getLineId());
		builder.setBusId(commonGps.getBusId());
		builder.setSourcePackage(ByteString.copyFrom(gpsDataByteArray));
		return builder.build();
	}
}
