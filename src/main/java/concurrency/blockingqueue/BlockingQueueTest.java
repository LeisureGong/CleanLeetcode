package concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author gonglei
 * @date 2020/7/23
 */
public class BlockingQueueTest {

	public static void main(String[] args) throws InterruptedException{
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		Consumer c = new Consumer(queue);

		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(p1);
		exec.execute(p2);
		exec.execute(p3);
		exec.execute(c);

		SECONDS.sleep(10);

		p1.stop();
		p2.stop();
		p3.stop();
		Thread.sleep(2000);
		exec.shutdown();
	}
}
