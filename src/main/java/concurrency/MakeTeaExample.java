package concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author gonglei
 * @date 2020/7/21
 */

public class MakeTeaExample {
	private static final Logger log = LoggerFactory.getLogger(MakeTeaExample.class);

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);


		FutureTask<String> ft2 = new FutureTask<String>(new T2Task());
		FutureTask<String> ft1 = new FutureTask<String>(new T1Task(ft2));

		executorService.submit(ft1);
		executorService.submit(ft2);
		executorService.shutdown();
	}

	static class T1Task implements Callable<String> {

		private FutureTask<String> ft2;
		public T1Task(FutureTask<String> ft2) {
			this.ft2 = ft2;
		}

		@Override
		public String call() throws Exception {
			log.info("T1:洗水壶...");
			TimeUnit.SECONDS.sleep(1);

			log.info("T1:烧开水...");
			TimeUnit.SECONDS.sleep(15);

			String t2Result = ft2.get();
			log.info("T1 拿到T2的 {}, 开始泡茶",t2Result);
			return "T1:上茶";
		}
	}

	static class T2Task implements Callable<String> {
		@Override
		public String call() throws Exception {
			log.info("T2:洗茶壶...");
			TimeUnit.SECONDS.sleep(1);

			log.info("T2:洗茶杯...");
			TimeUnit.SECONDS.sleep(2);

			log.info("T2:拿茶叶...");
			TimeUnit.SECONDS.sleep(1);
			return "T2:福鼎白茶拿到了";
		}
	}
}
