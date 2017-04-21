package com.test.nettytest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.socket.SocketChannel;

/**
 * 保存所有连接上来的客户端的信息
 */
public class GPSClientMasterDataMap
{
	private static Map<String, GPSClientDetailData> map = new ConcurrentHashMap<String, GPSClientDetailData>();

	/**
	 * 根据 SocketChannel 移除元素并返回该元素的ID
	 */
	public static String removeBySocketChannel(SocketChannel socketChannel)
	{
		String clientId = null;
		for (Entry entry : map.entrySet())
		{
			if (((GPSClientDetailData) entry.getValue()).getSocketChannel() == socketChannel)
			{
				clientId = (String) entry.getKey();
				map.remove(entry.getKey());
			}
		}
		return clientId;
	}

	/**
	 * 根据 clientId 移除元素并返回是否成功
	 */
	public static boolean removeByClientId(String clientId)
	{
		for (Entry entry : map.entrySet())
		{
			if (entry.getKey() == clientId)
			{
				map.remove(entry.getKey());
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查 Map 里是否已经有 clientId 的记录了
	 */
	public static boolean isContainsKey(String clientId)
	{
		if (map.containsKey(clientId))
			return true;
		else
			return false;
	}

	/**
	 * 向 Map 里增加元素，或者替换指定 clientId 所对应的内容。
	 */
	public static void addOrReplace(String clientId, GPSClientDetailData clientDetailData)
	{
		if (!map.containsKey(clientId))
			map.put(clientId, clientDetailData);
		else
			map.replace(clientId, clientDetailData);
	}

	/**
	 * 用新值替换指定的 clientId 原来所对应的内容
	 */
	public static void replace(String clientId, GPSClientDetailData clientDetailData)
	{
		map.replace(clientId, clientDetailData);
	}

	/**
	 * 根据 clientId 取得相对应的内容
	 */
	public static GPSClientDetailData getValueByKey(String clientId)
	{
		if (map.containsKey(clientId))
			return (GPSClientDetailData) map.get(clientId);
		else
			return null;
	}

	/**
	 * 取得当前客户ID列表
	 */
	public static List<String> getClientList()
	{
		if (map.size() > 0)
			return new ArrayList<String>(map.keySet());
		else
			return null;
	}
	
	/**
	 * 根据 clientId 获取相对应的 SocketChannel
	 */
	public static SocketChannel getSocketChannelByClientId(String clientId)
	{
		if (map.containsKey(clientId))
		{
			return ((GPSClientDetailData) map.get(clientId)).getSocketChannel();
		}
		else
		{
			return null;
		}			
	}
	
	/**
	 * 显示里面所有的元素
	 */
	public static String getAllEntry()
	{
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Entry entry : map.entrySet())
		{
			sb.append((++i)+":[");
			sb.append(entry.getKey()+":");
			sb.append(((GPSClientDetailData) entry.getValue()).toString()+"]");
		}		
		return sb.toString();
	}
}
