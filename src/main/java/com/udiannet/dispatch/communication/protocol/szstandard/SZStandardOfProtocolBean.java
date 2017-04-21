package com.udiannet.dispatch.communication.protocol.szstandard;

import java.io.Serializable;

/**
 * 按照锐明提供给博康的深标协议 1.5 版本，对协议基本组成进行封装的类
 */
public class SZStandardOfProtocolBean implements Serializable
{
	/**
	 * 起始标识
	 */
	private String headMark;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 报文长度
	 */
	private String messageLength;
	/**
	 * 发送序列号
	 */
	private String sendingSerialNum;
	/**
	 * 命令字
	 */
	private String commandCode;
	/**
	 * 接收方类型
	 */
	private String receiverType;
	/**
	 * 发送方类型
	 */
	private String senderType;
	/**
	 * 接收方地址
	 */
	private String receiverAddress;
	/**
	 * 发送方地址
	 */
	private String senderAddress;
	/**
	 * 基本数据
	 */
	private String basicData;
	/**
	 * 校验
	 */
	private String checkCode;

	public SZStandardOfProtocolBean(String gpsOriginalString)
	{
		gpsOriginalString = gpsOriginalString.trim();
		this.headMark = gpsOriginalString.substring(0, 4); // 4
		this.version = gpsOriginalString.substring(4, 8); // 4
		this.messageLength = gpsOriginalString.substring(8, 12); // 4
		this.sendingSerialNum = gpsOriginalString.substring(12, 14); // 2
		this.commandCode = gpsOriginalString.substring(14, 16); // 2
		this.receiverType = gpsOriginalString.substring(16, 18); // 2
		this.senderType = gpsOriginalString.substring(18, 20); // 2
		this.receiverAddress = gpsOriginalString.substring(20, 28); // 8
		this.senderAddress = gpsOriginalString.substring(28, 36); // 8
		this.basicData = gpsOriginalString.substring(36, gpsOriginalString.length() - 4); // n
		this.checkCode = gpsOriginalString.substring(gpsOriginalString.length() - 4); // 4
	}

	public String getHeadMark()
	{
		return headMark;
	}

	public String getVersion()
	{
		return version;
	}

	public String getMessageLength()
	{
		return messageLength;
	}

	public String getSendingSerialNum()
	{
		return sendingSerialNum;
	}

	public String getCommandCode()
	{
		return commandCode;
	}

	public String getReceiverType()
	{
		return receiverType;
	}

	public String getSenderType()
	{
		return senderType;
	}

	public String getReceiverAddress()
	{
		return receiverAddress;
	}

	public String getSenderAddress()
	{
		return senderAddress;
	}

	public String getBasicData()
	{
		return basicData;
	}

	public String getCheckCode()
	{
		return checkCode;
	}
}
