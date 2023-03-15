package com.xzx.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 谢子轩
 * @date 2023/03/14 15/13
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCall myCall = new MyCall();
        FutureTask futureTask = new FutureTask(myCall);
        new Thread(futureTask).start();
        new Thread(new FutureTask(myCall)).start();
        String o = (String) futureTask.get();
        System.out.println(o);

    }
}

class MyCall implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("finish");
        return "1234";
    }
}
