package com.udiannet.dispatch.communication.protocol.szstandard;

import java.net.Authenticator.RequestorType;

import com.google.protobuf.ByteString;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.*;

public class SZStandardOfEncoderByProtobuf
{
	/**
	 * 应答协议的封装
	 */
	public static SZStandardProtocol encoderToResp(SZStandardProtocol req)
	{
		SZStandardProtocol.Builder builder = SZStandardProtocol.newBuilder();
		builder.setVersion("1");
		
		builder.setProtocolVersion(req.getProtocolVersion());//版本号
		
		//报文长度

		builder.setSendingSerialNum(SZStandardUtil.getSendingSerialNum()); //发送序列号
		

		builder.setCmdType(SZStandardUtil.CMDTYPE_MAP.get(req.getCmdType())); //命令字
		
		builder.setReceiverType(req.getSenderType()); //接收方类型
		
		builder.setSenderType(req.getReceiverType());//发送方类型

		builder.setReceiverAddress(req.getSenderAddress()); //接收方地址
		
		builder.setSenderAddress(req.getReceiverAddress()); //发送方地址
		
		//数据内容
		return builder.build();
	}
	
	/**
	 * 终端注册应答（中心主发），命令字0xA0
	 */
	public static pRegisterResp getPRegisterResp(tRegisterReq req)
	{
		pRegisterResp.Builder builder = pRegisterResp.newBuilder();
		
		builder.setVersion("1");
		builder.setBusId(req.getCommonGps().getBusId());
		builder.setLineId(req.getCommonGps().getLineId());
		builder.setLastestTerminalSoftwareVersion(req.getTerminalSoftwareVersion());
		builder.setTerminalSerial(req.getTerminalSerial());
		builder.setIsPassed(true);
		builder.setSendTime(System.currentTimeMillis());

		return builder.build();
	}
}
