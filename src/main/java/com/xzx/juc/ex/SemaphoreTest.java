package com.xzx.juc.ex;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢子轩
 * @date 2023/03/14 15/52
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore sp = new Semaphore(3);
        CountDownLatch cdl = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    sp.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sp.release();
                    cdl.countDown();
                }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finish");
    }
}
