package com.test.nettytest.server;

import com.test.nettytest.kafka.KafkaCachedConsumer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ReferenceCountUtil;

public class GPSStringFrameDecodeAndCheck extends LengthFieldBasedFrameDecoder
{

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception
	{
//		if(in.hasArray())
//			System.out.println("堆缓冲区");
//		else			
//			System.out.println("直接缓冲区"); //结果是“直接缓冲区”
		
		
		
//		ByteBuf buf = Unpooled.copiedBuffer(in);
//		in.readerIndex(in.readableBytes());
		
		//合法性校验
		
//		
//		byte[] bufArray = buf.array();
//		//Unpooled.copiedBuffer(array)
//		System.out.println("开始输出 byte -->");
//		//int i = 0;
//		for(byte b:bufArray)
//		{
//			System.out.print(b+",");
//			//i++;
//		}
//		System.out.println("");
//		System.out.println("<-- 结束输出 byte");
//		ReferenceCountUtil.release(buf);
		
		
		
		String msgString = ByteBufUtil.hexDump(in.readBytes(in.readableBytes()));
//		String msgString = ByteBufUtil.hexDump(buf);
//		ReferenceCountUtil.release(buf);
		//KafkaCachedConsumer.queue.offer(msgString);
//		System.out.println("接收到如下消息：[" + msgString + "]");
		return msgString;
	}

	public GPSStringFrameDecodeAndCheck(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip)
	{
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

}
