package com.udiannet.dispatch.communication.protocol.szstandard;

import java.io.Serializable;

/**
 * 按照锐明提供给博康的深标协议 1.5 版本，对“基本数据”（70字节）部分进行解码并封装成类
 */
public class SZStandardOfBasicDataDecoderBean implements Serializable
{
	/**
	 * 1 城市区号 BCD 3 字节
	 */
	private String cityCode; // 1 城市区号
	/**
	 * 2 行业代码 HEX 1 字节
	 */
	private String industryCode;// 2 行业代码
	/**
	 * 3 企业代码 HEX 1 字节
	 */
	private String enterpriseCode;// 3 企业代码
	/**
	 * 4 车辆编码 ASCⅡ 8 字节
	 */
	private String busCode;// 4 车辆编码
	/**
	 * 5 线路号 ASCⅡ或国标码 8 字节
	 */
	private String lineCode;// 5 线路号
	/**
	 * 6 停车场序号 HEX 1 字节
	 */
	private String parkingNum;// 6 停车场序号
	/**
	 * 7 停车场编号 BCD
	 */
	private String parkingCode;// 7 停车场编号
	/**
	 * 8 站点序号 HEX 1 字节
	 */
	private String siteNum;// 8 站点序号
	/**
	 * 9 站点编号 BCD 8 字节
	 */
	private String siteCode;// 9 站点编号
	/**
	 * 10 车辆位置经度 BCD 5 字节
	 */
	private String longitude;// 10 车辆位置经度
	/**
	 * 11 车辆位置纬度 BCD 4 字节
	 */
	private String latitude;// 11 车辆位置纬度
	/**
	 * 12 日期、时间 BCD 6 字节
	 */
	private String datetimeStr;// 12 日期、时间
	/**
	 * 13 速度（传感器） BCD 2 字节
	 */
	private String speedFromSensor;// 13 速度（传感器）
	/**
	 * 14 速度（GPS） BCD 2 字节
	 */
	private String speedFromGPS;// 14 速度（GPS）
	/**
	 * 15 走向 BCD 2 字节
	 */
	private String direction;// 15 走向
	/**
	 * 16 车内外温度 BCD 2 字节
	 */
	private String temperature;// 16 车内外温度
	/**
	 * 17 运营状态 HEX 4 字节
	 */
	private String operationStatus;// 17 运营状态
	/**
	 * 18 累计里程 BCD 4 字节
	 */
	private String accumulativeMileage;// 18 累计里程	

	public String getCityCode()
	{
		return cityCode;
	}

	public String getIndustryCode()
	{
		return industryCode;
	}

	public String getEnterpriseCode()
	{
		return enterpriseCode;
	}

	public String getBusCode()
	{
		return busCode;
	}

	public String getLineCode()
	{
		return lineCode;
	}

	public String getParkingNum()
	{
		return parkingNum;
	}

	public String getParkingCode()
	{
		return parkingCode;
	}

	public String getSiteNum()
	{
		return siteNum;
	}

	public String getSiteCode()
	{
		return siteCode;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public String getDatetimeStr()
	{
		return datetimeStr;
	}

	public String getSpeedFromSensor()
	{
		return speedFromSensor;
	}

	public String getSpeedFromGPS()
	{
		return speedFromGPS;
	}

	public String getDirection()
	{
		return direction;
	}

	public String getTemperature()
	{
		return temperature;
	}

	public String getOperationStatus()
	{
		return operationStatus;
	}

	public String getAccumulativeMileage()
	{
		return accumulativeMileage;
	}
	
	public SZStandardOfBasicDataDecoderBean(String gpsBasicData)
	{
		gpsBasicData = gpsBasicData.trim();
		if (gpsBasicData.trim().length() == 140)
		{
			this.cityCode = gpsBasicData.substring(0, 6); // 1 城市区号 BCD
			this.industryCode = SZStandardUtil.hexToDecDecoder(gpsBasicData.substring(6, 8));// 2 行业代码 HEX
			this.enterpriseCode = SZStandardUtil.hexToDecDecoder(gpsBasicData.substring(8, 10));// 3 企业代码 HEX
			this.busCode = SZStandardUtil.getRealStringFromHexString(gpsBasicData.substring(10, 26));// 4 车辆编码 ASCII

			this.lineCode = SZStandardUtil.getRealStringFromHexString(gpsBasicData.substring(26, 42));// 5 线路号 ASCII
			if (this.lineCode.indexOf("无法解析") == -1) //把线路号前面无意义的“0”去掉
				this.lineCode = SZStandardUtil.removeZeroFromHead(this.lineCode);

			this.parkingNum = SZStandardUtil.hexToDecDecoder(gpsBasicData.substring(42, 44));// 6 停车场序号 HEX
			this.parkingCode = gpsBasicData.substring(44, 60);// 7 停车场编号 BCD
			this.siteNum = gpsBasicData.substring(60, 62);// 8 站点序号 BCD
			this.siteCode = gpsBasicData.substring(62, 78);// 9 站点编号 BCD

			try
			{
				if (!"".equals(SZStandardUtil.parseFloat(gpsBasicData.substring(82, 88), 4)))
					this.longitude = Integer.parseInt(gpsBasicData.substring(78, 82)) + "°"
							+ SZStandardUtil.parseFloat(gpsBasicData.substring(82, 88), 4) + "'";// 10 车辆位置经度 BCD 0121°XX.xxxx′
				else
					this.longitude = gpsBasicData.substring(78, 88) + " (无法解析)";
			}
			catch (Exception e)
			{
				this.longitude = gpsBasicData.substring(78, 88) + " (无法解析)";
			}

			try
			{
				if (!"".equals(SZStandardUtil.parseFloat(gpsBasicData.substring(90, 96), 4)))
					this.latitude = Integer.parseInt(gpsBasicData.substring(88, 90)) + "°"
							+ SZStandardUtil.parseFloat(gpsBasicData.substring(90, 96), 4) + "'";// 11 车辆位置纬度 BCD 31°XX.xxxx′
				else
					this.latitude = gpsBasicData.substring(88, 96) + " (无法解析)";
			}
			catch (Exception e)
			{
				this.latitude = gpsBasicData.substring(88, 96) + " (无法解析)";
			}

			this.datetimeStr = gpsBasicData.substring(96, 108);// 12 日期、时间 BCD

			this.speedFromSensor = SZStandardUtil.parseFloat(gpsBasicData.substring(108, 112), 1) + " km/h";// 13 速度（传感器） BCD 2字节 xxx.x km/h
			if (" km/h".equals(this.speedFromSensor))
				this.speedFromSensor = gpsBasicData.substring(108, 112) + " (无法解析)";

			this.speedFromGPS = SZStandardUtil.parseFloat(gpsBasicData.substring(112, 116), 1) + " km/h";// 14 速度（GPS） BCD 2字节 xxx.x km/h
			if (" km/h".equals(this.speedFromGPS))
				this.speedFromGPS = gpsBasicData.substring(112, 116) + " (无法解析)";

			this.direction = SZStandardUtil.parseFloat(gpsBasicData.substring(116, 120), 1) + "°";// 15 走向 BCD 2字节 000.0～359.9°
			if ("°".equals(this.direction))
				this.direction = gpsBasicData.substring(116, 120) + " (无法解析)";

			try
			{
				this.temperature = "内:" + Integer.parseInt(gpsBasicData.substring(120, 122)) + "℃ 外:"
						+ Integer.parseInt(gpsBasicData.substring(122, 124)) + "℃";// 16 车内外温度 BCD 前1字节车内：xx℃
			}
			catch (Exception e)
			{
				this.temperature = gpsBasicData.substring(120, 124) + " (无法解析)";
			}

			this.operationStatus = gpsBasicData.substring(124, 132);// 17 运营状态 HEX

			this.accumulativeMileage = SZStandardUtil.parseFloat(gpsBasicData.substring(132, 140), 2) + " km";// 18 累计里程 BCD xx xx xx.xx km
			if (" km".equals(this.accumulativeMileage))
				this.accumulativeMileage = gpsBasicData.substring(132, 140) + " (无法解析)";
		}
	}
}