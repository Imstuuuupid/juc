package com.xzx.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 谢子轩
 * @date 2023/03/18 18/28
 */
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        String a, b;
        a = "lockA";
        b = "lockB";

        new Thread(new MyThread(a, b), "T1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new MyThread(b, a), "T2").start();

    }


}

class MyThread implements Runnable {

    private String a;
    private String b;

    public MyThread(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + "=> lock :" + a);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "=> lock :" + b);

            }

        }

    }
}
