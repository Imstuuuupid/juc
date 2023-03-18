package com.xzx.juc.pool;

import java.util.concurrent.*;

/**
 * @author 谢子轩
 * @date 2023/03/15 16/07
 */
public class ThreadPoolExecutorDemo02 {

    /**
     * 最大线程如何定义？
     * 1、cpu 密集型    几核就是几，或者x2
     * 2、io 密集型    找到最耗费io的线程数量，设置最大线程大于这个数量
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {

            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
