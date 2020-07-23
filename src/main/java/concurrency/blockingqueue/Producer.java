package concurrency.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 生产者线程
 * @author gonglei
 * @date 2020/7/23
 */
public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private BlockingQueue queue;
	private static AtomicInteger count = new AtomicInteger();

	public Producer (BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		String data = null;
		Random r = new Random();
		System.out.println("启动Producer");
		try {
			while (isRunning) {
				System.out.println("正在生产数据");
				SECONDS.sleep(1);
				data = "data:" + count.incrementAndGet();
				System.out.println("将数据：" + data + "放入队列。。。");
				if (!queue.offer(data,2, SECONDS)) {
					System.out.println("放入数据失败：" + data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("退出生产者线程！");
		}
	}

	public void stop() {
		isRunning = false;
	}
}
