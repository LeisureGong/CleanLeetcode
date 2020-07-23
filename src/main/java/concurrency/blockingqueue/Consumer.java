package concurrency.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author gonglei
 * @date 2020/7/23
 */
public class Consumer implements Runnable {

	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		System.out.println("启动生产者线程！");
		Random r = new Random();
		boolean isRunning = true;
		try {
			System.out.println("正在从队列中获取数据。。。");
			String data = queue.poll(2, SECONDS);
			if (null != data) {
				System.out.println("拿到数据 ： " + data);
				System.out.println("正在消费数据");
				SECONDS.sleep(1);
			} else {
				isRunning = false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("退出消费者线程");
		}
	}
}
