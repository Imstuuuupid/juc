package com.xzx.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author 谢子轩
 * @date 2023/03/15 16/57
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long temp = 100000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 10_0000_0000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }

        Long mid = (start + end) / 2;
        ForkJoinDemo f1 = new ForkJoinDemo(start, mid);
        f1.fork();
        ForkJoinDemo f2 = new ForkJoinDemo(mid + 1, end);
        f2.fork();
        return f1.join() + f2.join();
    }
}
