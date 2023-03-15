package com.xzx.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 谢子轩
 * @date 2023/03/14 13/39
 */
public class A {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.incrment();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                data.decrment();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.incrment();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.incrment();
            }
        }, "D").start();
    }

}


class Data {
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void incrment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrment() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
