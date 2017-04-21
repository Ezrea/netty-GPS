package com.test.nettytest.kafka;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 主线程起一条线程，专门用来监听发送给 Kafka 前的一个缓冲区里的数据变化
 */
public class KafkaCachedMonitor implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("Kafka 缓冲区监视器已启动。");
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		//不断的检测缓存
		while (true)
		{
			//只要缓存不为空，就启用线程将数据发送至 Kafka
			while (!KafkaCachedConsumer.queue.isEmpty())
			{
				executor.execute(KafkaCachedConsumer.getInstance());
				System.out.printf("线程池信息 --> [大小: %d] [活跃: %d] [已完成: %d]\n", executor.getPoolSize(),
						executor.getActiveCount(), executor.getCompletedTaskCount());
			}

		}
	}

}
