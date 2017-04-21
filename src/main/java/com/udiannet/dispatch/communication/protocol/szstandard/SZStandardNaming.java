package com.udiannet.dispatch.communication.protocol.szstandard;

/**
 * 按照锐明提供给博康的深标协议 1.5 版本，对字段名字进行命名规范
 */
public final class SZStandardNaming
{
	private SZStandardNaming()
	{
	}

	/**
	 * 字段语言
	 */
	public enum FieldLanguage
	{
		/**
		 * 英文
		 */
		EN,
		/**
		 * 中文
		 */
		CN
	}

	/**
	 * 协议组成格式的字段命名
	 */
	public enum ProtocalFormat
	{
		/**
		 * 1、头标识 2字节
		 */
		HEADMARK,
		/**
		 * 2、版本号 2字节
		 */
		VERSION,
		/**
		 * 3、报文长度 2字节
		 */
		MESSAGELENGTH,
		/**
		 * 4、发送序列号 1字节
		 */
		SENDINGSERIALNUM,
		/**
		 * 5、命令字 1字节
		 */
		COMMANDCODE,
		/**
		 * 6、接收方类型 1字节
		 */
		RECEIVERTYPE,
		/**
		 * 7、发送方类型 1字节
		 */
		SENDERTYPE,
		/**
		 * 8、接收方地址 4字节
		 */
		RECEIVERADDRESS,
		/**
		 * 9、发送方地址 4字节
		 */
		SENDERADDRESS,
		/**
		 * 10、基本数据内容 n字节
		 */
		BASICDATA,
		/**
		 * 11、校验码 2字节
		 */
		CHECKCODE;

		/**
		 * 获取字段的命名
		 * 
		 * @param protocalFormatName 字段名
		 * @param fieldLanguage 命名语言
		 * @return
		 */
		public static String getName(ProtocalFormat protocalFormatName, FieldLanguage fieldLanguage)
		{
			switch (fieldLanguage)
			{
				case EN:
				{
					switch (protocalFormatName)
					{
						case HEADMARK:
							return "HeadMark";
						case VERSION:
							return "Version";
						case MESSAGELENGTH:
							return "MessageLength";
						case SENDINGSERIALNUM:
							return "SendingSerialNum";
						case COMMANDCODE:
							return "CommandCode";
						case RECEIVERTYPE:
							return "ReceiverType";
						case SENDERTYPE:
							return "SenderType";
						case RECEIVERADDRESS:
							return "ReceiverAddress";
						case SENDERADDRESS:
							return "SenderAddress";
						case BASICDATA:
							return "BasicData";
						case CHECKCODE:
							return "CheckCode";
						default:
							return null;
					}
				}
				case CN:
				{
					switch (protocalFormatName)
					{
						case HEADMARK:
							return "起始标识";
						case VERSION:
							return "版本号";
						case MESSAGELENGTH:
							return "报文长度";
						case SENDINGSERIALNUM:
							return "发送序列号";
						case COMMANDCODE:
							return "命令字";
						case RECEIVERTYPE:
							return "接收方类型";
						case SENDERTYPE:
							return "发送方类型";
						case RECEIVERADDRESS:
							return "接收方地址";
						case SENDERADDRESS:
							return "发送方地址";
						case BASICDATA:
							return "基本数据内容";
						case CHECKCODE:
							return "校验";
						default:
							return null;
					}
				}
				default:
					return null;
			}
		}
	}

	/**
	 * 协议基本组成里数据内容的字段命名
	 */
	public enum BasicDataFormat
	{
		/**
		 * 1、城市区号 BCD 3 字节
		 */
		CITYCODE, // 1、城市区号
		/**
		 * 2、行业代码 HEX 1 字节
		 */
		INDUSTRYCODE, // 2 、行业代码
		/**
		 * 3、企业代码 HEX 1 字节
		 */
		ENTERPRISECODE, // 3、 企业代码
		/**
		 * 4、车辆编码 ASCⅡ 8 字节
		 */
		BUSCODE, // 4、车辆编码
		/**
		 * 5、线路号 ASCⅡ或国标码 8 字节
		 */
		LINECODE, // 5、线路号
		/**
		 * 6、停车场序号 HEX 1 字节
		 */
		PARKINGNUM, // 6 、停车场序号
		/**
		 * 7、停车场编号 BCD
		 */
		PARKINGCODE, // 7、停车场编号
		/**
		 * 8、 站点序号 HEX 1 字节
		 */
		SITENUM, // 8、站点序号
		/**
		 * 9、站点编号 BCD 8 字节
		 */
		SITECODE, // 9、站点编号
		/**
		 * 10、车辆位置经度 BCD 5 字节
		 */
		LONGITUDE, // 10、车辆位置经度
		/**
		 * 11、车辆位置纬度 BCD 4 字节
		 */
		LATITUDE, // 11、车辆位置纬度
		/**
		 * 12、日期、时间 BCD 6 字节
		 */
		DATETIME, // 12、日期、时间
		/**
		 * 13、速度（传感器） BCD 2 字节
		 */
		SPEEDFROMSENSOR, // 13、速度（传感器）
		/**
		 * 14、速度（GPS） BCD 2 字节
		 */
		SPEEDFROMGPS, // 14、速度（GPS）
		/**
		 * 15、走向 BCD 2 字节
		 */
		DIRECTION, // 15、走向
		/**
		 * 16、车内外温度 BCD 2 字节
		 */
		TEMPERATURE, // 16、车内外温度
		/**
		 * 17、运营状态 HEX 4 字节
		 */
		OPERATIONSTATUS, // 17、运营状态
		/**
		 * 18、累计里程 BCD 4 字节
		 */
		ACCUMULATIVEMILEAGE;// 18、累计里程

		/**
		 * 获取字段的命名
		 * 
		 * @param messageDataFormatName 字段名称
		 * @param fieldLanguage 命名语言
		 * @return
		 */
		public static String getName(BasicDataFormat basicDataFormatName, FieldLanguage fieldLanguage)
		{
			switch (fieldLanguage)
			{
				case EN:
				{
					switch (basicDataFormatName)
					{
						case CITYCODE:
							return "CityCode";
						case INDUSTRYCODE:
							return "IndustryCode";
						case ENTERPRISECODE:
							return "EnterpriseCode";
						case BUSCODE:
							return "BusCode";
						case LINECODE:
							return "LineCode";
						case PARKINGNUM:
							return "ParkingNum";
						case PARKINGCODE:
							return "ParkingCode";
						case SITENUM:
							return "SiteNum";
						case SITECODE:
							return "SiteCode";
						case LONGITUDE:
							return "Longitude";
						case LATITUDE:
							return "Latitude";
						case DATETIME:
							return "Datetime";
						case SPEEDFROMSENSOR:
							return "SpeedFromSensor";
						case SPEEDFROMGPS:
							return "SpeedFromGPS";
						case DIRECTION:
							return "Direction";
						case TEMPERATURE:
							return "Temperature";
						case OPERATIONSTATUS:
							return "PperationStatus";
						case ACCUMULATIVEMILEAGE:
							return "AccumulativeMileage";
						default:
							return null;
					}
				}
				case CN:
				{
					switch (basicDataFormatName)
					{
						case CITYCODE:
							return "城市区号";
						case INDUSTRYCODE:
							return "行业代码";
						case ENTERPRISECODE:
							return "企业代码";
						case BUSCODE:
							return "车辆编码";
						case LINECODE:
							return "线路号";
						case PARKINGNUM:
							return "停车场序号";
						case PARKINGCODE:
							return "停车场编号";
						case SITENUM:
							return "站点序号";
						case SITECODE:
							return "站点编号";
						case LONGITUDE:
							return "车辆位置经度";
						case LATITUDE:
							return "车辆位置纬度";
						case DATETIME:
							return "日期时间";
						case SPEEDFROMSENSOR:
							return "速度（传感器）";
						case SPEEDFROMGPS:
							return "速度（GPS）";
						case DIRECTION:
							return "走向";
						case TEMPERATURE:
							return "车内外温度";
						case OPERATIONSTATUS:
							return "运营状态";
						case ACCUMULATIVEMILEAGE:
							return "累计里程";
						default:
							return null;
					}
				}	
				default:
					return null;
			}
		}
	}
}
