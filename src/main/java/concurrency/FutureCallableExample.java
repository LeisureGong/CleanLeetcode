package concurrency;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gonglei
 * @date 2020/7/21
 */
public class FutureCallableExample {

	private static final Logger log = LoggerFactory.getLogger(FutureCallableExample.class);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Callable<String> callable = () -> {
			log.info("进入 Callable 的call方法");
			// 模拟子线程任务，在此睡眠2s
			// 由于call方法会抛出Exception, 这里不需要像Runnable的run方法那样try/catch
			Thread.sleep(5000);
			return "Hello from Callable";
		};

		log.info("提交 Callable 到线程池");
		double startTime = System.nanoTime();
		Future<String> future = executorService.submit(callable);
		log.info("主线程继续执行");
		log.info("主线程等待获取Future结果");
		while(!future.isDone()) {
			System.out.println("Task is still not done");
			Thread.sleep(5);
			double elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000;

			// 如果程序运行时间大于1s， 则取消子线程的运行
			if (elapsedTimeInSec > 100) {
				future.cancel(true);
			}
		}
		// Future.get() blocks until the result is available

		// 通过isCancelled方法判断程序是否被取消，如果被取消，则打印日志
		// 如果没被取消，则正常调用get()方法
		if(!future.isCancelled()) {
			log.info("子线程任务已完成");
			String result = future.get();
			log.info("主线程获取到Future结果 :{}",result);
		} else {
			log.warn("子线程任务被取消");
		}

		executorService.shutdown();
	}
}
