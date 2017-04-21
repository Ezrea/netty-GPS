package com.test.nettytest.decoder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

public class GPSStringDecoder extends ByteToMessageDecoder
{
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
	{	
		
		String msgString = in.readBytes(in.readableBytes()).toString(CharsetUtil.UTF_8);
//		System.out.println(msgString);
		//服务器控制端发送过来的命令
		if (msgString.length() > 0 && "ctrl".equals(msgString.substring(0, 4)))
		{
			//System.out.println("Controller:"+msgString);
			out.add(msgString);
		}
		//GPS终端传输过来的数据
		else
		{
			in.readerIndex(0);
			String gpsString = ByteBufUtil.hexDump(in.readBytes(in.readableBytes()));
//			System.out.println("gps");
			//以“faf5”开头的字串就是 GPS 数据串，然后把解码后的字串
			if (gpsString.length() > 0 && "faf5".equalsIgnoreCase(gpsString.substring(0, 4)))
			{
				out.add(gpsString);
			}
		}
		//ReferenceCountUtil.release(in);
	}
}
