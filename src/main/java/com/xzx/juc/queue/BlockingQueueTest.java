package com.xzx.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢子轩
 * @date 2023/03/14 20/00
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws Exception {
        test4();

    }

    /**
     * 抛出异常
     *
     */
    public static void test1() throws Exception {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("b"));

//        查看队首元素
        System.out.println(blockingQueue.element());

        System.out.println("================");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，不抛出异常
     *
     * @throws Exception
     */
    public static void test2() throws Exception {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

//        有返回值，无异常抛出
        System.out.println(blockingQueue.offer("e"));

        System.out.println("=======================================");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll());


    }

    /**
     * 阻塞 等待，不会超时
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        ArrayBlockingQueue ab = new ArrayBlockingQueue(3);

        ab.put("a");
        ab.put("b");
        ab.put("c");
//        ab.put("d");

        System.out.println("==========");

        System.out.println(ab.take());
        System.out.println(ab.take());
        System.out.println(ab.take());
        System.out.println(ab.take());
    }


    /**
     * 超时等待，有返回值，不会抛出异常
     *
     * @throws Exception
     */
    public static void test4() throws Exception {
        ArrayBlockingQueue abq = new ArrayBlockingQueue(3);

        System.out.println(abq.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(abq.offer("b", 1, TimeUnit.SECONDS));
        System.out.println(abq.offer("c", 1, TimeUnit.SECONDS));
        System.out.println(abq.offer("d", 3, TimeUnit.SECONDS));

        System.out.println("===============");

        System.out.println(abq.poll(1, TimeUnit.SECONDS));
        System.out.println(abq.poll(1, TimeUnit.SECONDS));
        System.out.println(abq.poll(1, TimeUnit.SECONDS));
        System.out.println(abq.poll(1, TimeUnit.SECONDS));


    }
}
