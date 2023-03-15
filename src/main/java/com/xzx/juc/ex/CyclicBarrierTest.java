package com.xzx.juc.ex;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author 谢子轩
 * @date 2023/03/14 15/36
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(7, () -> System.out.println("All finish!!"));
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {

                try {
                    cb.await(3,TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName() + " finish!");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
