package com.xzx.juc.ex;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢子轩
 * @date 2023/03/14 15/28
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch cdl = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish");

                cdl.countDown();
            }, String.valueOf(i)).start();
        }

        cdl.await(3, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println("cost time : " + (end-start)/1000 );
        System.out.println("all finish");
    }
}
