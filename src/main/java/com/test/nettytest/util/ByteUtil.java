package com.test.nettytest.util;

import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteUtil {

	/**
	 * 将int数值转换为byte
	 *
	 * @param value
	 *            要转换的int值
	 * @return byte值
	 */
	public static byte intToByte(int value) {
		return (byte) value;
	}

	/**
	 * 将byte数值转换为int
	 *
	 * @param value
	 *            要转换的byte值
	 * @return int值
	 */
	public static int byteToInt(byte value) {
		return value & 0xFF;
	}

	/**
	 * int16整数转换二2字节的二进制数
	 *
	 * @param i
	 *            int16整数
	 * @return 2字节的二进制数
	 */
	public static byte[] int16ToBytes(int i) {
		byte[] res = new byte[2];
		res[1] = (byte) i;
		res[0] = (byte) (i >>> 8);
		return res;
	}

	/**
	 * 2字节的二进制数转换为int16整数
	 *
	 * @param bytes
	 *            2字节的二进制数
	 * @return int16整数
	 */
	public static int bytesToInt16(byte[] bytes) {
		int res = ((bytes[0] << 8) | ((bytes[1] << 24) >>> 24));
		return res;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
	 *
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和bytesToInt2（）配套使用
	 *
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 *
	 * @param src
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt32(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
				| ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
	 *
	 * @param src
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt322(byte[] src, int offset) {
		int value;
		value = (int) (((src[offset] & 0xFF) << 24) | ((src[offset + 1] & 0xFF) << 16) | ((src[offset + 2] & 0xFF) << 8)
				| (src[offset + 3] & 0xFF));
		return value;
	}

	/**
	 * 将short数值转换为占两个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和shortToBytes2（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] shortToBytes(short data) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data & 0xff00) >> 8);
		return bytes;
	}

	/**
	 * 将short数值转换为占两个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和shortToBytes（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] shortToBytes2(short data) {
		byte[] bytes = new byte[2];
		bytes[1] = (byte) (data & 0xff);
		bytes[0] = (byte) ((data & 0xff00) >> 8);
		return bytes;
	}

	/**
	 * 将long数值转换为占八个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和longToBytes2（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] longToBytes(long data) {
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data >> 8) & 0xff);
		bytes[2] = (byte) ((data >> 16) & 0xff);
		bytes[3] = (byte) ((data >> 24) & 0xff);
		bytes[4] = (byte) ((data >> 32) & 0xff);
		bytes[5] = (byte) ((data >> 40) & 0xff);
		bytes[6] = (byte) ((data >> 48) & 0xff);
		bytes[7] = (byte) ((data >> 56) & 0xff);
		return bytes;
	}

	/**
	 * 将long数值转换为占八个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和longToBytes（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] longToBytes2(long data) {
		byte[] bytes = new byte[8];
		bytes[7] = (byte) (data & 0xff);
		bytes[6] = (byte) ((data >> 8) & 0xff);
		bytes[5] = (byte) ((data >> 16) & 0xff);
		bytes[4] = (byte) ((data >> 24) & 0xff);
		bytes[3] = (byte) ((data >> 32) & 0xff);
		bytes[2] = (byte) ((data >> 40) & 0xff);
		bytes[1] = (byte) ((data >> 48) & 0xff);
		bytes[0] = (byte) ((data >> 56) & 0xff);
		return bytes;
	}

	/**
	 * 将八位bytes数组转换为long数值，本方法适用于(低位在前，高位在后)的顺序。 和bytesToLong2（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static long bytesToLong(byte[] bytes) {
		return (0xffL & (long) bytes[0]) | (0xff00L & ((long) bytes[1] << 8)) | (0xff0000L & ((long) bytes[2] << 16))
				| (0xff000000L & ((long) bytes[3] << 24)) | (0xff00000000L & ((long) bytes[4] << 32))
				| (0xff0000000000L & ((long) bytes[5] << 40)) | (0xff000000000000L & ((long) bytes[6] << 48))
				| (0xff00000000000000L & ((long) bytes[7] << 56));
	}

	/**
	 * 将八位bytes数组转换为long数值，本方法适用于(高位在前，低位在后)的顺序。 和bytesToLong（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static long bytesToLong2(byte[] bytes) {
		return (0xffL & (long) bytes[7]) | (0xff00L & ((long) bytes[6] << 8)) | (0xff0000L & ((long) bytes[5] << 16))
				| (0xff000000L & ((long) bytes[4] << 24)) | (0xff00000000L & ((long) bytes[3] << 32))
				| (0xff0000000000L & ((long) bytes[2] << 40)) | (0xff000000000000L & ((long) bytes[1] << 48))
				| (0xff00000000000000L & ((long) bytes[0] << 56));
	}

	/**
	 * 将float数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和floatToBytes2（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] floatToBytes(float data) {
		int intBits = Float.floatToIntBits(data);
		return intToBytes(intBits);
	}

	/**
	 * 将float数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和floatToBytes（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] floatToBytes2(float data) {
		int intBits = Float.floatToIntBits(data);
		return intToBytes2(intBits);
	}

	/**
	 * 四位byte数组中取float数值，本方法适用于(低位在前，高位在后)的顺序，和和bytesToFloat2（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static float bytesToFloat(byte[] bytes) {
		return Float.intBitsToFloat(bytesToInt32(bytes, 0));
	}

	/**
	 * 四位byte数组中取float数值，本方法适用于(高位在前，低位在后)的顺序，和和bytesToFloat（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static float bytesToFloat2(byte[] bytes) {
		return Float.intBitsToFloat(bytesToInt322(bytes, 0));
	}

	/**
	 * 将double数值转换为占八个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和doubleToBytes2（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] doubleToBytes(double data) {
		long intBits = Double.doubleToLongBits(data);
		return longToBytes(intBits);
	}

	/**
	 * 将double数值转换为占八个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。 和floatToBytes（）配套使用
	 *
	 * @param data
	 * @return
	 */
	public static byte[] doubleToBytes2(double data) {
		long intBits = Double.doubleToLongBits(data);
		return longToBytes2(intBits);
	}

	/**
	 * 八位byte数组中取double数值，本方法适用于(低位在前，高位在后)的顺序，和和bytesToDouble2（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static double bytesToDouble(byte[] bytes) {
		long l = bytesToLong(bytes);
		System.out.println(l);
		return Double.longBitsToDouble(l);
	}

	/**
	 * 八位byte数组中取double数值，本方法适用于(高位在前，低位在后)的顺序，和和bytesToDouble（）配套使用
	 *
	 * @param bytes
	 * @return
	 */
	public static double bytesToDouble2(byte[] bytes) {
		long l = bytesToLong(bytes);
		System.out.println(l);
		return Double.longBitsToDouble(l);
	}
	
	public static byte[] byteToByte(byte[] bytes,int length){
		if (bytes == null){
			byte[] bytesTemp = new byte[length];
			Arrays.fill(bytesTemp, (byte) 0);
			return bytes;
		}
	
		if (bytes.length > length || bytes.length < length) {
			return Arrays.copyOf(bytes, length);
		} else {
			return bytes;
		}
	}

	public static byte[] stringToBytes(String data, int byteLength,String charsetName) {
		Charset charset=Charset.forName(charsetName);
		return stringToBytes(data,byteLength,charset);
	}
	
	public static byte[] stringToBytes(String data, int byteLength,Charset charsetName) {
		if (data == null || data.isEmpty()) {
			byte[] bytes = new byte[byteLength];
			Arrays.fill(bytes, (byte) 0);
			return bytes;
		}
		byte[] bytes = stringToBytes(data,charsetName);
		if (bytes.length > byteLength || bytes.length < byteLength) {
			return Arrays.copyOf(bytes, byteLength);
		} else {
			return bytes;
		}
	}

	public static byte[] stringToBytes(String data, String charsetName) {
		Charset charset = Charset.forName(charsetName);
		return stringToBytes(data,charset);
	}
	public static byte[] stringToBytes(String data, Charset charset) {
		return data.getBytes(charset);
	}

	public static byte[] stringToBytes(String data) {
		return stringToBytes(data, "GBK");
	}

	public static String bytesToString(byte[] bytes, String charsetName) {
		return bytesToString(bytes,Charset.forName(charsetName));
	}
	
	public static String bytesToString(byte[] bytes, Charset charset) {
		return new String(bytes, charset);
		
	}

	public static String bytesToString(byte[] bytes) {
		return bytesToString(bytes, "GBK");
	}

	/**
	 * BCD码转为10进制串(阿拉伯数据)
	 *
	 * @param bytes
	 *            BCD码
	 * @return 10进制串
	 */
	public static String bcdToStr(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		// temp.toString().substring(0, 1).equalsIgnoreCase("0") ?
		// temp.toString().substring(1) : temp.toString();
		return temp.toString();
	}

	/**
	 * BCD码转为16进制串(阿拉伯数据)
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

	
	/**
	 * 10进制串转为BCD码
	 *
	 * @param asc
	 *            10进制串
	 * @return BCD码
	 */
	public static byte[] strToBcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	public static String bytesToHexString(byte value) {
		StringBuffer sb = new StringBuffer();
		String str = Integer.toHexString(0xFF & value);
		if (str.length() < 2) {
			sb.append(0);
		}
		sb.append(str.toUpperCase());
		return sb.toString();
	}

	private static String hexStr = "0123456789ABCDEF";
	private static String[] binaryArray = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111" };

	/**
	 *
	 * @param str
	 * @return 二进制数组转换为二进制字符串 2-2
	 */
	public static String bytes2BinStr(byte[] bArray) {

		String outStr = "";
		int pos = 0;
		for (byte b : bArray) {
			// 高四位
			pos = (b & 0xF0) >> 4;
			outStr += binaryArray[pos];
			// 低四位
			pos = b & 0x0F;
			outStr += binaryArray[pos];
		}
		return outStr;
	}

	/**
	 *
	 * @param bytes
	 * @return 将二进制数组转换为十六进制字符串 2-16
	 */
	public static String bin2HexStr(byte[] bytes) {

		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex; // +" "
		}
		return result;
	}

	/**
	 *
	 * @param hexString
	 * @return 将十六进制转换为二进制字节数组 16-2
	 */
	public static byte[] hexStr2BinArr(String hexString) {
		// hexString的长度对2取整，作为bytes的长度
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte high = 0;// 字节高四位
		byte low = 0;// 字节低四位
		for (int i = 0; i < len; i++) {
			// 右移四位得到高位
			high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
			low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
			bytes[i] = (byte) (high | low);// 高地位做或运算
		}
		return bytes;
	}

	/**
	 *
	 * @param hexString
	 * @return 将十六进制转换为二进制字符串 16-2
	 */
	public static String hexStr2BinStr(String hexString) {
		return bytes2BinStr(hexStr2BinArr(hexString));
	}

	public static byte[] Int2BCDBytes(int input) {
		int length = 1;
		int tmp = input;
		while (tmp/10!=0) {
			length++;
			tmp = tmp/10;
		}
		int bcdLength;
		if (length%2==0){
			bcdLength = length/2;
		}else {
			bcdLength = length/2 + 1;
		}
		byte[] result = new byte[bcdLength];
		byte tmpChar;
		for (int i=bcdLength-1; i>=0; i--) {
			tmpChar =(byte) (input%100);
			result[i] = (byte) (((tmpChar / 10) << 4) + ((tmpChar % 10) & 0x0F));
			input /= 100;
		}

		return result;
	}


}
