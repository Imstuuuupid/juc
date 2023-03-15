package com.xzx.juc.unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 *
 * java.util.ConcurrentModificationException  并发编程修改异常
 *
 * @author 谢子轩
 * @date 2023/03/14 14/13
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        解决方法
//        1、Collections.synchronizedList(new ArrayList<>())
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
