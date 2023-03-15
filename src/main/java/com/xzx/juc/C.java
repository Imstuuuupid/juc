package com.xzx.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition定向唤醒
 *
 * @author 谢子轩
 * @date 2023/03/14 13/48
 */
public class C {

    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }).start();
    }

}

class Data3 {
    private int number = 0;
    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (number != 0) {
                conditionA.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "=>" + "AAA");
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 1) {
                conditionB.await();
            }
            number = 2;
            System.out.println(Thread.currentThread().getName() + "=>" + "BBB");
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 2) {
                conditionC.await();
            }
            number = 0;
            System.out.println(Thread.currentThread().getName() + "=>" + "CCC");
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
