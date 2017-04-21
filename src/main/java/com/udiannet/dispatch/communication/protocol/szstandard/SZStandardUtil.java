package com.udiannet.dispatch.communication.protocol.szstandard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.ByteString;
import com.test.nettytest.util.GpsUtil;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.CmdType;

/**
 * 按照锐明提供给博康的深标协议 1.5 版本写的辅助工具类
 */
public class SZStandardUtil
{
	/**
	 * 发送序列号
	 */
	private volatile static int sendingSerialNum = 0;

	public static int getSendingSerialNum()
	{
		if (sendingSerialNum == 255)
			sendingSerialNum = 1;
		else
			sendingSerialNum++;
		return sendingSerialNum;
	}

	/**
	 * 主发与应答的命令字对应关系
	 */
	public static final Map<CmdType, CmdType> CMDTYPE_MAP = new ConcurrentHashMap<CmdType, CmdType>()
	{
		{
			put(CmdType.REGISTER_REQ, CmdType.REGISTER_RESP);
			put(CmdType.TERMINAL_REPORT_REQ, CmdType.TERMINAL_REPORT_RESP);
			put(CmdType.IC_CARD_OPT_REQ, CmdType.IC_CARD_OPT_RESP);
			put(CmdType.ARR_LEA_STOP_MSG, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.ARR_LEA_STARTEND_STOP_MSG, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.IN_OUT_STATION_MSG, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.ABNORMAL_ALARM_MSG, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.TERMINAL_REPORT_REQ, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.IC_CARD_OPT_REQ, CmdType.TERMINAL_KEY_RESP);
			put(CmdType.TO_TERMINAL_MESSAGE_RESP, CmdType.TERMINAL_KEY_RESP);
		}
	};

	/**
	 * 检验 gps 原始字符串是否符合深标的协议组成格式
	 * 
	 * @param gpsOriginalString gps原始字符串
	 */
	public static boolean checkGpsOriginalString(String gpsOriginalString)
	{
		if (null == gpsOriginalString || gpsOriginalString.length() < 1)
			return false;

		//全部转换为小写
		gpsOriginalString = gpsOriginalString.trim().toLowerCase();

		//gps原始字符串的实际长度
		int gpsStringLen = gpsOriginalString.length();

		//1、是否是“faf5”开头
		if (gpsStringLen < 4 || !"faf5".equals(gpsOriginalString.substring(0, 4)))
			return false;

		//2、报文长度是否正确
		if (gpsStringLen < 12)
			return false;
		else
		{
			try
			{
				//因为这里的 gpsOriginalString 是16进制串，所以要把报文里填充的实际长度*2
				int messageLen = Integer.parseInt(gpsOriginalString.substring(8, 12), 16) * 2;
				//12是指协议里“报文长度”没有包含的前字符串长度，4是指没有包含的“校验”字符串长度
				if (messageLen != gpsStringLen - 12 - 4)
					return false;
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}

		//3、校验码是否匹配
		//报文里最后的“校验”内容本身有误
		if (Integer.parseInt(gpsOriginalString.substring(gpsStringLen - 4), 16) < 1)
			return false;
		int sum = 0;
		for (int i = 4; i < gpsStringLen - 4; i += 2)
		{
			sum += Integer.parseInt(gpsOriginalString.substring(i, i + 2), 16);
			if (sum > 65535)
				sum -= 65536;
		}
		if (sum != Integer.parseInt(gpsOriginalString.substring(gpsStringLen - 4), 16))
			return false;

		return true;
	}

	/**
	 * 检验 gps 原始二进制流数组是否符合深标的协议组成格式
	 */
	public static boolean checkGpsOriginalByteArray(byte[] gpsOriginalByteArray)
	{
		int len = gpsOriginalByteArray.length;

		if (len < 20)
			return false; //至少20个字节

		//1、是否是“faf5”开头

		if (!"faf5"
				.equalsIgnoreCase(byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 0, 2).toByteArray())))
			return false;

		//2、报文长度是否正确
		if (Integer.parseInt(byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, 4, 2).toByteArray()), 16)
				+ 8 != len)
			return false;

		//3、校验码是否匹配
		//报文里最后的“校验”内容本身有误
		int chk = Integer.parseInt(
				byteArrayToHexString(ByteString.copyFrom(gpsOriginalByteArray, len - 2, 2).toByteArray()), 16);

		if (chk < 1)
			return false;
		String beCheckedHexString = byteArrayToHexString(
				ByteString.copyFrom(gpsOriginalByteArray, 2, len - 4).toByteArray());
		int sum = 0;
		for (int i = 0; i < beCheckedHexString.length(); i += 2)
		{
			sum += Integer.parseInt(beCheckedHexString.substring(i, i + 2), 16);
			if (sum > 65535)
				sum -= 65536;
		}
		if (sum != chk)
			return false;

		return true;
	}

	/**
	 * 把一串 16 进制字符串，转换为 10 进制字符串，假如转换失败，则原串返回。
	 * 用于对接收到的字符串进行解析
	 * 
	 * @param hexString 16 进制字符串
	 */
	public static String hexToDecDecoder(String hexString)
	{
		if (null == hexString)
			return null;

		int len = hexString.length();

		if (len % 2 != 0)
			return hexString + " (无法解析)";

		if (len == 0)
			return "";
		if (len == 1)
			return Integer.parseInt(hexString, 16) + "";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i += 2)
		{
			try
			{
				sb.append(Integer.parseInt(hexString.substring(i, i + 2), 16));
			}
			catch (NumberFormatException e)
			{
				return hexString + " (无法解析)";
			}
		}
		return sb.toString();
	}

	/**
	 * 根据一串十六进制字符串（每两位代表一位实际意义的字符），转义成ASCII码，再转义成实际意义的字符串
	 */
	public static String getRealStringFromHexString(String hexString)
	{
		if (null == hexString)
			return null;

		//先把末尾的“0”字符去掉，因为这些“0”是补位用的，其本身无意思
		hexString = removeZeroFromLast(hexString);

		int len = hexString.length();

		if (len % 2 != 0)
			return hexString + " (无法解析)";
		if (len == 0)
			return "";
		if (len == 1)
			return Integer.parseInt(hexString, 16) + "";

		StringBuilder sb = new StringBuilder();
		// 十六进制字符串，每两个字符代表一个实际意义上的字符
		for (int i = 0; i < hexString.length(); i += 2)
		{
			// 获得两位16进制字符转义后的ASCII码
			int asciiInt = Integer.parseInt(hexString.substring(i, i + 2), 16);
			sb.append((char) asciiInt);
		}

		return sb.toString();
	}

	/**
	 * 去除数字开头无意义的“0”
	 */
	public static String removeZeroFromHead(String str)
	{
		if (str == null)
			return null;

		int len = str.length();

		if (len == 0)
			return "";

		while (len > 0 && str.charAt(0) == '0')
		{
			str = str.substring(1, len);
			len = str.length();
		}
		return str;
	}

	/**
	 * 去除数字末尾无意义的“0”
	 */
	public static String removeZeroFromLast(String str)
	{
		if (str == null)
			return null;

		int len = str.length();

		if (len == 0)
			return "";

		while (len > 0 && str.charAt(len - 1) == '0')
		{
			str = str.substring(0, len - 1);
			len = str.length();
		}
		return str;
	}

	/**
	 * 去除数字末尾无意义的“0”
	 */
	public static byte[] removeZeroFromLast(byte[] bytes)
	{
		int bytesLen = bytes.length;
		if (bytesLen < 1)
			return null;

		int i = 0;
		for (; i < bytesLen; i++)
		{
			if (bytes[i] == (byte) 0)
			{
				break;
			}
		}

		byte[] newBytes = new byte[i];
		System.arraycopy(bytes, 0, newBytes, 0, i);
		return newBytes;
	}

	/**
	 * 根据小数位数，把一串字符转换为浮点数
	 * 
	 * @param floatStr 没有小数点的浮点数字符串
	 * @param decimal 小数位数
	 */
	public static String parseFloat(String floatStr, int decimal)
	{
		//使用 try...catch 主要是为了防止 Integer.parseInt 出错
		try
		{
			if (floatStr == null)
				return "";
			int len = floatStr.length();
			if (len == 0)
				return "";

			//小数位数比字符串长度长，表示数值是小数，而且可能还要在前补0
			if (len <= decimal)
			{
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < decimal - len; i++)
				{
					sb.append("0");
				}
				return "0." + sb.toString() + removeZeroFromLast(floatStr);
			}
			//没有小数点
			if (decimal == 0)
				return Integer.parseInt(floatStr) + "";

			//小数位数为负数，相当于在原值后面补0
			if (decimal < 0)
			{
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < Math.abs(decimal); i++)
				{
					sb.append("0");
				}
				return Integer.parseInt(floatStr) + sb.toString();
			}

			return Integer.parseInt(floatStr.substring(0, len - decimal))
					+ ("".equals(removeZeroFromLast(floatStr.substring(len - decimal))) ? ""
							: "." + removeZeroFromLast(floatStr.substring(len - decimal)));
		}
		catch (Exception e) // Integer.parseInt 出错
		{
			return "";
		}
	}

	/**
	 * 字节数组转换成 16 进制
	 */
	public static String byteArrayToHexString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
		{
			String tmp = Integer.toHexString(b);
			if (tmp.length() > 1)
				sb.append(tmp.substring(tmp.length() - 2));
			else
				sb.append("0").append(tmp);
		}
		return sb.toString();
	}

	/**
	 * 把字节数组转换为 ASCII 码对应的字符
	 */
	public static String byteArrayToAsciiString(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
		{
			sb.append((char) b);
		}
		return sb.toString();
	}

	/**
	 * 10进制串转为BCD码
	 */
	public static byte[] decimalToBcd(String dec)
	{
		int len = dec.length();
		int mod = len % 2;

		if (mod != 0)
		{
			dec = "0" + dec;
			len = dec.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2)
		{
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = dec.getBytes();
		int j, k;

		for (int p = 0; p < dec.length() / 2; p++)
		{
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9'))
			{
				j = abt[2 * p] - '0';
			}
			else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z'))
			{
				j = abt[2 * p] - 'a' + 0x0a;
			}
			else
			{
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9'))
			{
				k = abt[2 * p + 1] - '0';
			}
			else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z'))
			{
				k = abt[2 * p + 1] - 'a' + 0x0a;
			}
			else
			{
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * BCD码转为10进制串(阿拉伯数据)
	 */
	public static String bcdToDecimal(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++)
		{
			sb.append((byte) ((bytes[i] & 0xf0) >>> 4));
			sb.append((byte) (bytes[i] & 0x0f));
		}
		return sb.toString().substring(0, 1).equalsIgnoreCase("0") && sb.toString().length() > bytes.length * 2
				? sb.toString().substring(1) : sb.toString();
		//return sb.toString();
	}

	/**
	 * 16 进制转二进制
	 */
	public static String hexStringToBinaryString(String hexString)
	{
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++)
		{
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	/**
	 * 把 int 转换为 boolean，只要是“0”就是“false”，其它为“true”
	 */
	public static boolean intToBoolean(int i)
	{
		if (i == 0)
			return false;
		return true;
	}

	/**
	 * 把 gps 原始字符串，按照协议的组成格式进行拆分
	 * 
	 * @param gpsOriginalString gps 原始字符串
	 * @param fieldLanguage 要显示的字段的语言：英文、中文
	 */
	public static Map<String, String> decodeByProtocalFormat(String gpsOriginalString,
			SZStandardNaming.FieldLanguage fieldLanguage)
	{
		//先校验字符串是否正确
		if (!checkGpsOriginalString(gpsOriginalString))
			return null;

		Map<String, String> map = new ConcurrentHashMap<String, String>();
		//版本号
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.VERSION, fieldLanguage),
				gpsOriginalString.substring(4, 8));
		//报文长度
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.MESSAGELENGTH, fieldLanguage),
				gpsOriginalString.substring(8, 12));
		//发送序列号
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.SENDINGSERIALNUM,
				fieldLanguage), gpsOriginalString.substring(12, 14));
		//命令字
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.COMMANDCODE, fieldLanguage),
				gpsOriginalString.substring(14, 16));
		//接收方类型
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.RECEIVERTYPE, fieldLanguage),
				gpsOriginalString.substring(16, 18));
		//发送方类型
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.SENDERTYPE, fieldLanguage),
				gpsOriginalString.substring(18, 20));
		//接收方地址
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.RECEIVERADDRESS, fieldLanguage),
				gpsOriginalString.substring(20, 28));
		//发送方地址
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.SENDERADDRESS, fieldLanguage),
				gpsOriginalString.substring(28, 36));
		//数据内容
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.BASICDATA, fieldLanguage),
				gpsOriginalString.substring(36, gpsOriginalString.length() - 4));
		//校验
		map.put(SZStandardNaming.ProtocalFormat.getName(SZStandardNaming.ProtocalFormat.CHECKCODE, fieldLanguage),
				gpsOriginalString.substring(gpsOriginalString.length() - 4));

		return map;
	}

	/**
	 * 把 gps 原始字符串里的基本数据，按照协议的说明进行拆分
	 * 
	 * @param gpsBasicDataString gps 原始字符串里的基本数据，它的固定长度为140（16进制字符串）
	 * @param fieldLanguage 要显示的字段的语言：英文、中文
	 */
	public static Map<String, String> decodeByBasicDataFormat(String gpsBasicDataString,
			SZStandardNaming.FieldLanguage fieldLanguage)
	{
		//基本数据的长度固定为140
		if (gpsBasicDataString.length() != 140)
			return null;

		Map<String, String> map = new ConcurrentHashMap<String, String>();

		// 1 城市区号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.CITYCODE, fieldLanguage),
				gpsBasicDataString.substring(0, 6));

		// 2 行业代码
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.INDUSTRYCODE, fieldLanguage),
				gpsBasicDataString.substring(6, 8));
		// 3 企业代码
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.ENTERPRISECODE,
				fieldLanguage), gpsBasicDataString.substring(8, 10));
		// 4 车辆编码
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.BUSCODE, fieldLanguage),
				gpsBasicDataString.substring(10, 26));
		// 5 线路号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.LINECODE, fieldLanguage),
				gpsBasicDataString.substring(26, 42));
		// 6 停车场序号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.PARKINGNUM, fieldLanguage),
				gpsBasicDataString.substring(42, 44));
		// 7 停车场编号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.PARKINGCODE, fieldLanguage),
				gpsBasicDataString.substring(44, 60));
		// 8 站点序号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.SITENUM, fieldLanguage),
				gpsBasicDataString.substring(60, 62));
		// 9 站点编号
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.SITECODE, fieldLanguage),
				gpsBasicDataString.substring(62, 78));
		// 10 车辆位置经度
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.LONGITUDE, fieldLanguage),
				gpsBasicDataString.substring(78, 88));
		// 11 车辆位置纬度
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.LATITUDE, fieldLanguage),
				gpsBasicDataString.substring(88, 96));
		// 12 日期、时间
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.DATETIME, fieldLanguage),
				gpsBasicDataString.substring(96, 108));
		// 13 速度（传感器）
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.SPEEDFROMSENSOR,
				fieldLanguage), gpsBasicDataString.substring(108, 112));
		// 14 速度（GPS）
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.SPEEDFROMGPS, fieldLanguage),
				gpsBasicDataString.substring(112, 116));
		// 15 走向
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.DIRECTION, fieldLanguage),
				gpsBasicDataString.substring(116, 120));
		// 16 车内外温度
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.TEMPERATURE, fieldLanguage),
				gpsBasicDataString.substring(120, 124));
		// 17 运营状态
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.OPERATIONSTATUS,
				fieldLanguage), gpsBasicDataString.substring(124, 132));
		// 18 累计里程
		map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.ACCUMULATIVEMILEAGE,
				fieldLanguage), gpsBasicDataString.substring(132, 140));

		return map;
	}

	/**
	 * 把 gps 原始字符串里的基本数据，按照协议的说明进行拆分，再把数据翻译成可以让人阅读的内容
	 * 
	 * @param gpsBasicDataString gps 原始字符串里的基本数据
	 * @param fieldLanguage 要显示的字段的语言：英文、中文
	 */
	public static Map<String, String> decodeByBasicDataFormatToRead(String gpsBasicDataString,
			SZStandardNaming.FieldLanguage fieldLanguage)
	{
		Map<String, String> map = decodeByBasicDataFormat(gpsBasicDataString, fieldLanguage);

		if (null != map)
		{
			//车辆编码，16进制->10进制->字符
			map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.BUSCODE, fieldLanguage),
					GpsUtil.getRealStringFromHexString(map.get(SZStandardNaming.BasicDataFormat
							.getName(SZStandardNaming.BasicDataFormat.BUSCODE, fieldLanguage))));
			//线路号，16进制->10进制->字符
			map.put(SZStandardNaming.BasicDataFormat.getName(SZStandardNaming.BasicDataFormat.LINECODE, fieldLanguage),
					GpsUtil.getRealStringFromHexString(map.get(SZStandardNaming.BasicDataFormat
							.getName(SZStandardNaming.BasicDataFormat.LINECODE, fieldLanguage))));
			//停车场序号，16进制->10进制
			map.put(SZStandardNaming.BasicDataFormat
					.getName(SZStandardNaming.BasicDataFormat.PARKINGNUM,
							fieldLanguage),
					Integer.parseInt(map.get(SZStandardNaming.BasicDataFormat
							.getName(SZStandardNaming.BasicDataFormat.PARKINGNUM, fieldLanguage)), 16) + "");
			//站点序号，16进制->10进制
			map.put(SZStandardNaming.BasicDataFormat
					.getName(SZStandardNaming.BasicDataFormat.PARKINGNUM,
							fieldLanguage),
					Integer.parseInt(map.get(SZStandardNaming.BasicDataFormat
							.getName(SZStandardNaming.BasicDataFormat.PARKINGNUM, fieldLanguage)), 16) + "");

		}

		return map;
	}
}
