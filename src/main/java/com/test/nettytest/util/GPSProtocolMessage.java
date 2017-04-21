package com.test.nettytest.util;

/**
 * 按深标协议的基本组成制定的协议消息包
 */
public class GPSProtocolMessage
{
	/********************** 固定部分 **********************/
	/**
	 * 头标识
	 */
	private final String HEADMARK = "faf5";
	/**
	 * 版本号
	 */
	private final String VERSION = "0010";
	/**
	 * 发送序列号，全部都为00
	 */
	private final String SENDNO = "00";
	/**
	 * 接收方类型：“ff”为车载终端
	 */
	private final String RECEIVERTYPE = "ff";
	/**
	 * 发送方类型：“02”为调度控制台
	 */
	private final String SENDERTYPE = "02";
	/**
	 * 接收方地址
	 */
	private final String RECEIVERADDRESS = "00000000";
	/**
	 * 发送方地址
	 */
	private final String SENDERADDRESS = "00000000";
	/********************** 固定部分 **********************/

	/********************** 可变部分 **********************/
	/**
	 * 报文长度
	 */
	private String messageLength;
	/**
	 * 命令字
	 */
	private String commandCode;
	/**
	 * 数据内容
	 */
	private String messageData;
	/**
	 * 校验码
	 */
	private String checkCode;

	/********************** 可变部分 **********************/

	public final String getMessageLength()
	{
		return messageLength;
	}

	public final void setMessageLength(String messageLength)
	{
		this.messageLength = messageLength;
	}

	public final String getCommandCode()
	{
		return commandCode;
	}

	public final void setCommandCode(String commandCode)
	{
		this.commandCode = commandCode;
	}

	public final String getMessageData()
	{
		return messageData;
	}

	public final void setMessageData(String messageData)
	{
		this.messageData = messageData;
	}

	public final String getCheckCode()
	{
		return checkCode;
	}

	public final void setCheckCode(String checkCode)
	{
		this.checkCode = checkCode;
	}

	/**
	 * 根据命令字和数据内容部分，自动生成整个命令串
	 * 
	 * @throws Exception
	 */
	public GPSProtocolMessage(String commandCode, String messageData) throws Exception
	{
		//		if (commandCode.length() % 2 != 0 || messageData.length() % 2 != 0)
		//			throw new Exception("输入的命令字或数据内容有误！ ");

		if (commandCode.length() % 2 != 0)
			throw new Exception("输入的命令字有误！ ");

		//命令字
		this.commandCode = commandCode;

		//数据内容。有些数据假如有中文字符的话，要特别作处理
		if ("11".equals(commandCode) || "14".equals(commandCode))
		{
			//长度大于44，就有可能包含汉字内容
			if (messageData.length() > 44)
			{
				String cn = messageData.substring(44);
				byte[] bytes = cn.getBytes("gbk");
				StringBuilder sb = new StringBuilder();
				for (byte b : bytes)
				{
					String tmp = Integer.toHexString(b);
					if (tmp.length() > 2)
						tmp = tmp.substring(tmp.length() - 2);
					sb.append(tmp);
				}
				this.messageData = messageData.substring(0, 44) + sb.toString();
			}
			else
			{
				this.messageData = messageData;
			}
		}
		else
		{
			this.messageData = messageData;
		}

		//计算报文长度，从 SENDNO 到 messageData 的总长度
		int len = (SENDNO + this.commandCode + RECEIVERTYPE + SENDERTYPE + RECEIVERADDRESS + SENDERADDRESS + this.messageData)
				.length();
		this.messageLength = GpsUtil.intToHexString(len / 2, 4);

		//计算校验码，从 VERSION 到 messageData 的无进位累加和
		this.checkCode = GpsUtil.getCheckString(VERSION + messageLength + SENDNO + this.commandCode + RECEIVERTYPE
				+ SENDERTYPE + RECEIVERADDRESS + SENDERADDRESS + this.messageData);
	}

	@Override
	public String toString()
	{
		return HEADMARK + VERSION + messageLength + SENDNO + commandCode + RECEIVERTYPE + SENDERTYPE + RECEIVERADDRESS
				+ SENDERADDRESS + messageData + checkCode;
	}

}
