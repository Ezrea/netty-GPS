package com.test.nettytest.mytest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.test.nettytest.client.NettyClientUtil;

public class MyTest
{
	private String getFormatTime(long duration)
	{
		StringBuilder sb = new StringBuilder();
		duration = duration / 1000;

		//取秒
		int s = (int) (duration % 60);
		
		sb.append(s + "秒");

		//取分钟
		int m = (int) (duration - s > 0 ? (duration - s) / 60 : 0);

		if (m > 0)
		{
			if (m >= 60)
			{
				//取小时
				int h = (m - m % 60) / 60;
				m = m % 60;
				sb.insert(0, h + "小时" + m + "分钟");
			}
			else
			{
				sb.insert(0, m + "分钟");
			}
		}

		return sb.toString();
	}
	
	@Test
	public void test01()
	{
		//		byte[] aa = {66,83,49,49,49,49,49,68};

		//		System.out.println(new String(aa));

		//		byte[] aa = {0,0,8,56};
		//		System.out.println(Float.parseFloat(SZStandardUtil.bcdToDecimal(
		//				ByteString.copyFrom(aa, 0, 4).toByteArray()))/100);
		//		

		//		byte[] bytes = {81, -128, 0};
		//		byte[] tmp = ByteString.copyFrom(bytes, 0, bytes.length).toByteArray();

		//		System.out.println(SZStandardUtil.byteArrayToHexString(tmp));
		//System.out.println(Integer.parseInt(ByteString.copyFrom(bytes, 0, 1).toStringUtf8(),16));

		//		System.out.println(SZStandardUtil.byteArrayToAsciiString(bytes));
		//		
		//		byte[] bb = SZStandardUtil.byteArrayToHexString(tmp).getBytes();
		//		for(byte b:bb)
		//			System.out.println(b);
		//		System.out.println(new String(bb));

		//		System.out.println(SZStandardUtil.hexStringToBinaryString("8c")+"  "+Boolean.valueOf((SZStandardUtil.hexStringToBinaryString("8c").charAt(0)+"")));

		//		byte[] gpsBasicDataByteArray = {81,-128,0,1,0,66,83,49,49,49,49,49,68,48,48,48,48,90,0,0,0,1,10,2,3,4,5,6,7,8,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,23,4,8,16,33,81,0,0,0,0,0,0,0,0,-116,6,-1,-1,0,0,8,56};
		//		CommonGps commonGps = SZStandardOfDecoderByProtobuf.getCommonGpsInstance(gpsBasicDataByteArray);
		//		System.out.println(commonGps);

		//		byte[] bs1 = ByteString.copyFrom(gpsBasicDataByteArray, 13, 8).toByteArray();
		//		System.out.println(new String(bs1));

		//		String string = "1002030405060708";
		//		byte[] bs1 = SZStandardUtil.decimalToBcd(string);
		//		for(byte a:bs1)
		//			System.out.println(a);

		byte[] bs1 = { -6, -11, 0, 16, 0, -89, -43, 32, 2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 81, -128, 0, 1, 0, 66, 83, 49,
				49, 49, 49, 49, 68, 48, 48, 48, 48, 90, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 4, 9, 22, 84, 49, 0, 0, 0, 0, 0, 0, 0, 0, -116, 6, -1, -1, 0, 0, 8, 56,
				84, 49, 54, 49, 50, 50, 55, 57, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				66, 83, 49, 49, 49, 49, 49, 68, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 3, 0, 0, 7, 0, 52, 54, 48, 48, 49, 57, 55, 49, 51, 55, 50, 56, 53, 52, 48, 16, -53 };
		
//		System.out.println(SZStandardUtil.checkGpsOriginalByteArray(bs1));
		
//		System.out.println(CmdType.forNumber(ByteString.copyFrom(bs1, 7, 1).byteAt(0)));
		
//		System.out.println(Integer.toHexString((byte)-86));
		
//		System.out.println(Integer.parseInt("fa", 16));
//		
//		try
//		{
////			String ss = (new GPSProtocolMessage("41", "5180000101535A42434D313738303038350000000000000000000000000050000000000000008000000000000000000017010313163900000000000000009006FF0009975296")).toString();
//			String ss = "FAF5001000593C4502FF00000000000000005180000101535A42434D323337303330380000000000000000000000000019000000000000002501141167182233411917010406510700000663076000000004FF0015918226010101090008000AE5";
//			byte[] bb = GpsUtil.hexStringToByteArray(ss);
//			for(byte b:bb)	System.out.print(b+",");
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(GpsUtil.getRealStringFromHexString("535A42424C303335"));
		
//		for(int i=0;i<10;i++)
//		{
//			String uuid = UUID.randomUUID().toString();
//			System.out.println(uuid+" : "+uuid.substring(uuid.length()-5));
//		}
		
//		byte[] bb = "Aa".getBytes();
//		for(byte b:bb)
//		System.out.print(b+",");
		
//		byte[] bb;
//		try
//		{
//			bb = GpsUtil.hexStringToByteArray("faf5001000a7002002ff000000000000000051800001004253313131313144303030305a0000000100000000000000010700000000000000000000000000000000017022311285100000000000000008806ffff00000307543136313232373931000000000000000000000000000000000000000000000042533131313131440000000000000000000000000000000000000000000000000003000007000000000000000000000000000000000cc0");
//			for(byte b:bb)
//				System.out.print(b+",");
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(NettyClientUtil.getBusIdHexStringByUUID());
		
//		System.out.println(Integer.parseInt("fa", 16));
		
		long l = (61*60)*1000;
//		long l = 1000;
//		Date date = new Date(l);
		System.out.println(getFormatTime(l));
	}
}
