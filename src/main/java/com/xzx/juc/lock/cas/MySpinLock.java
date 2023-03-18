package com.xzx.juc.lock.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 谢子轩
 * @date 2023/03/18 18/12
 */
public class MySpinLock {

    private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void myLock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " 加锁");
        while (!threadAtomicReference.compareAndSet(null, thread)) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " 锁自旋中");
        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " 解锁");
    }

    public static void main(String[] args) {
        MySpinLock lock = new MySpinLock();
        new Thread(() -> {
            try {
                lock.myLock();
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                lock.myLock();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        }, "B").start();

    }
}
