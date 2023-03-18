package com.xzx.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author 谢子轩
 * @date 2023/03/15 17/54
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        System.out.println();

        test2();
        System.out.println();

        test3();
        System.out.println();

    }

    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0l;
        for (Long i = 0L; i < 10_0000_0000L; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + ", sum = " + sum);
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + ", sum = " + sum);
    }

    public static void test3() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + ", sum = " + sum);


    }
}
