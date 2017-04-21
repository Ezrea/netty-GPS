package com.test.nettytest.util;

import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.SZStandardProtocol;

import io.netty.channel.socket.SocketChannel;

/**
 * 连接上来的客户端的详细资料
 */
public class GPSClientDetailData
{
	/**
	 * 终端机唯一序列号
	 */
	private String clientId;
	/**
	 * 终端机已建立连接的SocketChannel
	 */
	private SocketChannel socketChannel;
	/**
	 * 是否已建立心跳机制
	 */
	private boolean isHeartBeatHandlerRunning;
	/**
	 * 把接收到的原包，按深标协议的基本组成进行拆分
	 */
	private SZStandardProtocol protocolPackage;
	
	public final String getClientId()
	{
		return clientId;
	}
	public final void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	public final SocketChannel getSocketChannel()
	{
		return socketChannel;
	}
	public final void setSocketChannel(SocketChannel socketChannel)
	{
		this.socketChannel = socketChannel;
	}
	public final boolean isHeartBeatHandlerRunning()
	{
		return isHeartBeatHandlerRunning;
	}
	public final void setHeartBeatHandlerRunning(boolean isHeartBeatHandlerRunning)
	{
		this.isHeartBeatHandlerRunning = isHeartBeatHandlerRunning;
	}	
	public final SZStandardProtocol getProtocolPackage()
	{
		return protocolPackage;
	}
	public final void setProtocolPackage(SZStandardProtocol protocolPackage)
	{
		this.protocolPackage = protocolPackage;
	}
	public GPSClientDetailData(String clientId, SocketChannel socketChannel)
	{
		this.clientId = clientId;
		this.socketChannel = socketChannel;
		this.isHeartBeatHandlerRunning = false;
	}
	
	public GPSClientDetailData(String clientId, SocketChannel socketChannel, SZStandardProtocol protocolPackage)
	{
		this.clientId = clientId;
		this.socketChannel = socketChannel;
		this.isHeartBeatHandlerRunning = false;
		this.protocolPackage = protocolPackage;
	}
	
	@Override
	public String toString()
	{
		return "GPSClientDetailData [clientId=" + clientId + ", socketChannel=" + socketChannel
				+ ", isHeartBeatHandlerRunning=" + isHeartBeatHandlerRunning + "]";
	}
}
