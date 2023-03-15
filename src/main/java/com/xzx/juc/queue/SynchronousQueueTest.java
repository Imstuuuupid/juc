package com.xzx.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue不一样，SynchronousQueue不存储元素
 * 如果put进去一个元素，不take出来的话put会一直阻塞
 *
 * @author 谢子轩
 * @date 2023/03/14 20/30
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
//        同步队列
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1").start();


        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take 1");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take 2");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " take 3");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}
