package com.udiannet.dispatch.communication.netty.server;

import java.util.List;

import com.test.nettytest.kafka.KafkaCachedConsumer;
import com.udiannet.dispatch.communication.protocol.szstandard.SZStandardOfDecoderByProtobuf;
import com.udiannet.dispatch.communication.protocol.szstandard.SZStandardUtil;
import com.udiannet.dispatch.communication.terminalaccess.model.protobuf.KafkaTopicDataProtobuf.SZStandardProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.AsciiHeadersEncoder.NewlineType;
import io.netty.util.ReferenceCountUtil;

public class GPSStringFrameDecodeAndCheck extends LengthFieldBasedFrameDecoder
{

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception
	{
		//合法性校验
		String msgString = ByteBufUtil.hexDump(in.readBytes(in.readableBytes()));
		if(!SZStandardUtil.checkGpsOriginalString(msgString))
			return null;

		//把包按协议组成进行分解
		in.readerIndex(0);
		byte[] bufArray = new byte[in.readableBytes()];
		in.getBytes(0, bufArray);
		in.readerIndex(bufArray.length); //一定要设这个！否则会报异常！
//		ReferenceCountUtil.release(in);

//		System.out.println("开始输出 byte -->");
//		//int i = 0;
//		for(byte b:bufArray)
//		{
//			System.out.print(b+",");
//			//i++;
//		}
//		System.out.println("");
//		System.out.println("<-- 结束输出 byte");
		
		SZStandardProtocol msg = SZStandardOfDecoderByProtobuf.decoderByProtocol(bufArray);
		
		return msg;
		
		//KafkaCachedConsumer.queue.offer(msgString);
//		System.out.println("接收到如下消息：[" + msgString + "]");
//		return msgString;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	public GPSStringFrameDecodeAndCheck(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip)
	{
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

}
