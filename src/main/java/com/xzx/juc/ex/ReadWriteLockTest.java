package com.xzx.juc.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 谢子轩
 * @date 2023/03/14 16/20
 */
public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                data.set(String.valueOf(finalI), UUID.randomUUID().toString().substring(0, 5));
            }, String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                data.get(String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }
    }


}

class Data {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Map<String, Object> map = new HashMap<>();

    public void set(String k, Object v) {
        try {
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "写" + k);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写" + k + " ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String k) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "读" + k);
            map.get(k);
            System.out.println(Thread.currentThread().getName() + "读" + k + " ok");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
