package com.udiannet.dispatch.communication.protocol.szstandard;

/**
 * 按照锐明提供给博康的深标协议 1.5 版本，对每个接收到的指令进行解码并封装成类
 */
public class SZStandardOfCommandDecoderBean
{
	/**
	 * 终端注册，命令字0x20（TCP连接时终端主发）
	 */
	public class CommandDecoder_20
	{
		/**
		 * 基本数据 70字节
		 */
		private SZStandardOfBasicDataDecoderBean basicDataBean;
		/**
		 * 终端程序版本号 32 字节 （ASCII）
		 */
		private String terminalProgramVersion;
		/**
		 * 终端序列号 32字节 (ASCII)
		 */
		private String terminalSerialNumber;
		/**
		 * 心跳时间 2字节（BCD xxxx s）
		 */
		private String heartbeatTime;
		/**
		 * 发送间隔里程 2字节 （BCD xx.xx km）
		 */
		private String sendIntervalMileage;
		/**
		 * 全程限速值 2字节 （BCD xxx.x km/h）
		 */
		private String speedLimit;
		/**
		 * IMSI卡号 15字节 (ASCII)
		 */
		private String imsi;

		public final SZStandardOfBasicDataDecoderBean getBasicDataBean()
		{
			return basicDataBean;
		}

		public final String getTerminalProgramVersion()
		{
			return terminalProgramVersion;
		}

		public final String getTerminalSerialNumber()
		{
			return terminalSerialNumber;
		}

		public final String getHeartbeatTime()
		{
			return heartbeatTime;
		}

		public final String getSendIntervalMileage()
		{
			return sendIntervalMileage;
		}

		public final String getSpeedLimit()
		{
			return speedLimit;
		}

		public final String getImsi()
		{
			return imsi;
		}

		public CommandDecoder_20(String gpsBasicDataString)
		{
			gpsBasicDataString = gpsBasicDataString.trim();
			if (gpsBasicDataString.length() == (70 + 32 + 32 + 2 + 2 + 2 + 15) * 2)
			{
				//基本数据 70字节
				this.basicDataBean = new SZStandardOfBasicDataDecoderBean(gpsBasicDataString.substring(0, 70 * 2));

				//终端程序版本号 32字节 （ASCII）
				this.terminalProgramVersion = SZStandardUtil
						.getRealStringFromHexString(gpsBasicDataString.substring(70 * 2, (70 + 32) * 2));

				//终端序列号 32字节 (ASCII)
				this.terminalSerialNumber = SZStandardUtil
						.getRealStringFromHexString(gpsBasicDataString.substring((70 + 32) * 2, (70 + 32 + 32) * 2));

				//心跳时间 2字节 （BCD xxxx s）
				try
				{
					this.heartbeatTime = Integer.parseInt(
							gpsBasicDataString.substring((70 + 32 + 32) * 2, (70 + 32 + 32 + 2) * 2), 16) + "";
				}
				catch (Exception e)
				{
					this.heartbeatTime = gpsBasicDataString.substring((70 + 32 + 32) * 2, (70 + 32 + 32 + 2) * 2)
							+ " (无法解析)";
				}

				//发送间隔里程 2字节 （BCD xx.xx km）
				this.sendIntervalMileage = SZStandardUtil.parseFloat(
						gpsBasicDataString.substring((70 + 32 + 32 + 2) * 2, (70 + 32 + 32 + 2 + 2) * 2), 2) + " km";
				if (" km".equals(this.sendIntervalMileage))
					this.sendIntervalMileage = gpsBasicDataString.substring((70 + 32 + 32 + 2) * 2,
							(70 + 32 + 32 + 2 + 2) * 2) + " (无法解析)";

				//全程限速值 2字节 （BCD xxx.x km/h）
				this.speedLimit = SZStandardUtil.parseFloat(
						gpsBasicDataString.substring((70 + 32 + 32 + 2 + 2) * 2, (70 + 32 + 32 + 2 + 2 + 2) * 2), 1)
						+ " km/h";
				if (" km/h".equals(this.speedLimit))
					this.speedLimit = gpsBasicDataString.substring((70 + 32 + 32 + 2 + 2) * 2,
							(70 + 32 + 32 + 2 + 2 + 2) * 2) + " (无法解析)";

				//IMSI卡号 15字节 (ASCII)
				this.imsi = SZStandardUtil
						.getRealStringFromHexString(gpsBasicDataString.substring((70 + 32 + 32 + 2 + 2 + 2) * 2));
			}
		}
	}

	/**
	 * 终端设备就绪信息，命令字0x40
	 */
	public class CommandDecoder_40
	{
		/**
		 * 基本数据 70字节
		 */
		private SZStandardOfBasicDataDecoderBean basicDataBean;
		/**
		 * 终端程序版本号 32 字节 （ASCII）
		 */
		private String terminalProgramVersion;
		/**
		 * 终端序列号 32字节 (ASCII)
		 */
		private String terminalSerialNumber;
		/**
		 * 心跳时间 2字节（BCD xxxx s）
		 */
		private String heartbeatTime;
		/**
		 * 发送间隔里程 2字节 （BCD xx.xx km）
		 */
		private String sendIntervalMileage;
		/**
		 * 全程限速值 2字节 （BCD xxx.x km/h）
		 */
		private String speedLimit;

		public final SZStandardOfBasicDataDecoderBean getBasicDataBean()
		{
			return basicDataBean;
		}

		public final String getTerminalProgramVersion()
		{
			return terminalProgramVersion;
		}

		public final String getTerminalSerialNumber()
		{
			return terminalSerialNumber;
		}

		public final String getHeartbeatTime()
		{
			return heartbeatTime;
		}

		public final String getSendIntervalMileage()
		{
			return sendIntervalMileage;
		}

		public final String getSpeedLimit()
		{
			return speedLimit;
		}

		public CommandDecoder_40(String gpsBasicDataString)
		{
			gpsBasicDataString = gpsBasicDataString.trim();
			if (gpsBasicDataString.length() == (70 + 32 + 32 + 2 + 2 + 2) * 2)
			{
				//基本数据 70字节
				this.basicDataBean = new SZStandardOfBasicDataDecoderBean(gpsBasicDataString.substring(0, 70 * 2));

				//终端程序版本号 32字节 （ASCII）
				this.terminalProgramVersion = SZStandardUtil
						.getRealStringFromHexString(gpsBasicDataString.substring(70 * 2, (70 + 32) * 2));

				//终端序列号 32字节 (ASCII)
				this.terminalSerialNumber = SZStandardUtil
						.getRealStringFromHexString(gpsBasicDataString.substring((70 + 32) * 2, (70 + 32 + 32) * 2));

				//心跳时间 2字节 （BCD xxxx s）
				try
				{
					this.heartbeatTime = Integer.parseInt(
							gpsBasicDataString.substring((70 + 32 + 32) * 2, (70 + 32 + 32 + 2) * 2), 16) + "";
				}
				catch (Exception e)
				{
					this.heartbeatTime = gpsBasicDataString.substring((70 + 32 + 32) * 2, (70 + 32 + 32 + 2) * 2)
							+ " (无法解析)";
				}

				//发送间隔里程 2字节 （BCD xx.xx km）
				this.sendIntervalMileage = SZStandardUtil.parseFloat(
						gpsBasicDataString.substring((70 + 32 + 32 + 2) * 2, (70 + 32 + 32 + 2 + 2) * 2), 2) + " km";
				if (" km".equals(this.sendIntervalMileage))
					this.sendIntervalMileage = gpsBasicDataString.substring((70 + 32 + 32 + 2) * 2,
							(70 + 32 + 32 + 2 + 2) * 2) + " (无法解析)";

				//全程限速值 2字节 （BCD xxx.x km/h）
				this.speedLimit = SZStandardUtil.parseFloat(gpsBasicDataString.substring((70 + 32 + 32 + 2 + 2) * 2), 1)
						+ " km/h";
				if (" km/h".equals(this.speedLimit))
					this.speedLimit = gpsBasicDataString.substring((70 + 32 + 32 + 2 + 2) * 2) + " (无法解析)";
			}
		}

	}

	/**
	 * 终端发送定时信息（车载终端->中心） ，命令字0x41
	 */
	public class CommandDecoder_41
	{
		/**
		 * 基本数据 70字节
		 */
		private SZStandardOfBasicDataDecoderBean basicDataBean;

		public final SZStandardOfBasicDataDecoderBean getBasicDataBean()
		{
			return basicDataBean;
		}

		public CommandDecoder_41(String gpsBasicDataString)
		{
			gpsBasicDataString = gpsBasicDataString.trim();
			if (gpsBasicDataString.length() == (70 * 2))
			{
				//基本数据 70字节
				this.basicDataBean = new SZStandardOfBasicDataDecoderBean(gpsBasicDataString);
			}
		}
	}
	
	/**
	 * POS机数据 31字节
	 */
	public class posData
	{
		/**
		 * 普通卡本站次数 2字节 （HEX）
		 */
		private String commonCardTimes;
		/**
		 * 普通卡本站金额 4字节 （HEX）
		 */
		private String commonCardMoney;
		/**
		 * 换乘优惠卡本站次数 2字节 （HEX）
		 */
		
		/**
		 * 换乘优惠卡本站金额 4字节 （HEX）
		 */

		/**
		 * 老年人卡本站次数 2字节 （HEX）
		 */
		
		/**
		 * 老年人卡本站金额 4字节 （HEX）
		 */
		
		/**
		 * 员工卡本站次数 2字节 （HEX）
		 */
		
		/**
		 * 员工卡本站金额 4字节 （HEX）
		 */
		
		/**
		 * 累计刷卡人数 2字节 （HEX）
		 */
		
		/**
		 * 累计刷卡金额 4字节 （HEX）
		 */
		
		/**
		 * 客流站号 1字节 （HEX）
		 */
	}
	
	/**
	 * 到站、离站信息（定点发送信息1），命令字0x42
	 */
	public class CommandDecoder_42
	{
		/**
		 * 基本数据 70字节
		 */
		private SZStandardOfBasicDataDecoderBean basicDataBean;
		/**
		 * 站间里程 2字节 （BCD xx.xx km）
		 */
		private String betweenStationMileage;
		/**
		 * POS机数据标志 1字节
		 */
		private String posDataMark;
		/**
		 * POS机数据 31字节
		 */
		
		/**
		 * 投币箱数据标志 1字节
		 */
		
		/**
		 * 投币箱 27字节
		 */
		
		/**
		 * 客流计 5字节
		 */
		
		/**
		 * 保留 2字节
		 */
	}

}
