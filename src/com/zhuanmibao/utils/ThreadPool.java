package com.zhuanmibao.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author cainli
 * 2013-12-19下午9:51:10
 */
public class ThreadPool {
	private ThreadPool() {
	}

	private static int CORE_POOL_SIZE = 3;

	private static int MAX_POOL_SIZE = 8;

	private static int KEEP_ALIVE_TIME = 10000;

	// 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
	private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
			10);

	// 线程工厂
	private static ThreadFactory threadFactory = new ThreadFactory() {
		private final AtomicInteger integer = new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "myThreadPool thread:"
					+ integer.getAndIncrement());
		}
	};

	// 线程池
	private static ThreadPoolExecutor threadPool;

	static {
		threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
				KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
	}

	public static void execute(Runnable runnable) {
		threadPool.execute(runnable);
	}

}
