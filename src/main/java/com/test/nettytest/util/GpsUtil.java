package com.test.nettytest.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.primitives.Bytes;
import com.test.nettytest.server.GPSServer;

public final class GpsUtil
{
//	private GpsUtil(){}
//	
//	private static Properties pps;
//	static
//	{
////		System.out.println(Object.class.getResource("/").getPath());
//		InputStream in = null;
//		try
//		{
////			new FileInputStream(Object.class.getResourceAsStream("/netty-server.properties"))
//			in = new BufferedInputStream(new FileInputStream(GpsUtil.class.getResource("").getPath()+"netty-server.properties"));
////			
////			in = new BufferedInputStream(GpsUtil.class.getResourceAsStream("netty-server.properties"));
//			pps.load(in);
//			
//			System.out.println("pps: "+pps.toString());
//		}
//		catch (Exception e)
//		{
//			try
//			{
//				if (in != null)
//					in.close();
//			}
//			catch (IOException e1)
//			{
//				in = null;
//			}
//		}
//		finally
//		{
//			try
//			{
//				if (in != null)
//					in.close();
//			}
//			catch (IOException e)
//			{
//				in = null;
//			}
//		}
//	}
	/**
	 * Netty 配置
	 */
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 25534;

	/**
	 * Kafka 配置
	 */
	public static final String KAFKASERVER = "192.168.1.105:9092";
	public static final String KAFKA_TOPIC = "java-topic";
	/**
	 * 关键数据应答
	 */
	public static final String[] KEYCOMMAND = { "42", "43", "44", "45", "46", "48", "91" };

	public static final Map<String, String> COMMAND_MAP = new HashMap<String, String>()
	{
		{
			put("02", "82"); //定时定距发送间隔修改命令
			put("06", "86"); //设置信息提示音命令			
			put("28", ""); //重启			
			put("11", "91"); //下发终端短信			
			put("13", "93"); //终端基本参数查询命令			
			put("14", "94"); //运营状态改变命令		
			put("1a", "9a"); //运营线路变更命令
		}
	};

	public enum CommonGPSDataName
	{
		CITYCODE, BUSCODE, LINECODE
	}

	public static String getPropertiesValueByKey(String key)
	{
		//GpsUtil.class.getResource("");
//		System.out.println("1 "+GpsUtil.class.getResource("/").getPath());
//		System.out.println("2 "+GpsUtil.class.getResource("").getPath());
//		
//		System.out.println("3 "+GpsUtil.class.getClassLoader().getResource("/").getPath());
//		System.out.println("4 "+GpsUtil.class.getClassLoader().getResource("").getPath());
		
		
//		try
//		{
//			System.out.println("1 " + GpsUtil.class.getResource("/").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try
//		{
//			System.out.println("2 " + GpsUtil.class.getResource("").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try
//		{
//			System.out.println("3 " + GpsUtil.class.getClassLoader().getResource("/").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try
//		{
//			System.out.println("4 " + GpsUtil.class.getClassLoader().getResource("").getPath());
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "";
		
		
		Properties pps = new Properties();		
		InputStream in = null;
		try
		{
			in = new BufferedInputStream(new FileInputStream(GpsUtil.class.getResource("").getPath()+"netty-server.properties"));
			pps.load(in);
			return pps.getProperty(key);
		}
		catch (Exception e)
		{
			try
			{
				if (in != null)
					in.close();
			}
			catch (IOException e1)
			{
				in = null;
			}
			return null;
		}
		finally
		{
			try
			{
				if (in != null)
					in.close();
			}
			catch (IOException e)
			{
				in = null;
			}
		}
	}

	/**
	 * 根据一串十六进制字符串（每两位代表一位实际意义的字符），转义成ASCII码，再转义成实际意义的字符串
	 */
	public static String getRealStringFromHexString(String hexString)
	{
		StringBuilder sb = new StringBuilder();
		// 十六进制字符串，每两个字符代表一个实际意义上的字符
		for (int i = 0; i < hexString.length(); i += 2)
		{
			// 获得两位16进制字符转义后的ASCII码
			if (!"00".equals(hexString.substring(i, i + 2)))
			{
				int asciiInt = Integer.parseInt(hexString.substring(i, i + 2), 16);
				sb.append((char) asciiInt);
			}
		}

		return sb.toString();
	}

	/**
	 * 把单个16进制的字符转换为十进制数值大小
	 * 
	 * @param hexString 单个16进制的字符
	 * @return
	 */
	private static byte hexStringToByte(String hexString)
	{
		return (byte) "0123456789abcdef".indexOf(hexString.toLowerCase());
	}

	/**
	 * 计算一串16进制字符串的无进位累加和
	 * 
	 * @param hexString
	 * @return
	 * @throws Exception
	 */
	public static String getCheckString(String hexString) throws Exception
	{
		int len = hexString.length();
		if (len <= 0 || len % 2 != 0) // 长度为0或基数时，不合法
		{
			throw new Exception("输入的字符串有误！ ");
		}

		char[] strArr = hexString.toCharArray();
		int sum = 0;
		for (int i = 0; i < len; i += 2)
		{
			int i1 = hexStringToByte(String.valueOf(strArr[i])) * 16 + hexStringToByte(String.valueOf(strArr[i + 1]));
			sum += i1;
		}

		String result = Integer.toHexString(sum);

		if (result.length() == 1)
			return "000" + result;
		else if (result.length() == 2)
			return "00" + result;
		else if (result.length() == 3)
			return "0" + result;
		else
			return result;
	}

	/**
	 * 将输入的16进制字符串转换为用于网络通信的字节数据
	 * 
	 * @param hexString 16进制字符串
	 * @return
	 * @throws Exception
	 */
	public static byte[] hexStringToByteArray(String hexString) throws Exception
	{
		int len = hexString.length();
		if (len <= 0 || len % 2 != 0) // 长度为0或基数时，不合法
		{
			throw new Exception("输入的16进制字符串有误！ ");
		}

		List<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < len; i += 2)
		{
			list.add((byte) Integer.parseInt(hexString.substring(i, i + 2), 16));
		}
		return Bytes.toArray(list);
	}

	/**
	 * 传入一串16进制的 GPS 字符串，根据协议进行解码，返回字符串里的命令字
	 * 
	 * @param gpsString 16进制的 GPS 字符串
	 * @return
	 */
	public static String getCommandString(String gpsString)
	{
		return gpsString.substring(14, 16);
	}

	/**
	 * 传入一串16进制的 GPS 字符串，根据协议进行解码，返回字符串里的包序列号
	 * 
	 * @param gpsString 16进制的 GPS 字符串
	 * @return
	 */
	public static String getPackageIndex(String gpsString)
	{
		return gpsString.substring(12, 14);
	}

	/**
	 * 计算从 1900 年到现在的秒数
	 * 
	 * @return
	 */
	public static String getDistanceSecond()
	{
		//		long d1 = 0L;
		//		try
		//		{
		//			d1 = (new SimpleDateFormat("yyyy-MM-dd")).parse("1900-01-01").getTime();
		//		}
		//		catch (ParseException e)
		//		{
		//			e.printStackTrace();
		//		}
		//		long d2 = System.currentTimeMillis();
		//		return Long.toHexString((d2 - d1) / 1000);

		//直接取当前时间
		return Long.toHexString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取终端序列号（仅限“20”命令字字串）
	 */
	public static String getClientId(String gpsString)
	{
		return gpsString.substring(240, 304);
	}

	/**
	 * 把十进制数字转换为指定位数的字符串
	 * 
	 * @param n 十进制数字
	 * @param size 字符串位数
	 */
	public static String intToHexString(int n, int size)
	{
		String s = Integer.toHexString(n);
		int len = s.length();
		if (len < size)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size - len; i++)
			{
				sb.append("0");
			}
			return sb.toString() + s;
		}
		return s;
	}

	/**
	 * 根据发送过来 GPS 数据串，假如里面包含了 GPS 基本数据，则从基本数据里取出一些常用的信息
	 */
	public static String getCommonGPSData(String gpsString, CommonGPSDataName dataName)
	{
		//包含了 GPS 基本数据串的 GPS 完整指令串的长度，至少要有180
		if (gpsString.length() < 180)
			return null;
		//取出 GPS 基本数据串
		String gpsBaseData = gpsString.substring(36, 177);

		switch (dataName)
		{
		case CITYCODE:
			return gpsBaseData.substring(0, 6);
		case BUSCODE:
			return gpsBaseData.substring(10, 26);
		case LINECODE:
			return gpsBaseData.substring(26, 42);
		}
		return null;
	}
}
