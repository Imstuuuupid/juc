package com.xzx.juc.lock.dcl;

/**
 * @author 谢子轩
 * @date 2023/03/18 16/50
 */
public class Singleton {

    private Singleton() {

    }

    private static enum SingletonEnum {
        SINGLETON_ENUM;
        private Singleton singleton;

        private SingletonEnum() {
            singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }


    public static void main(String[] args) {
        Singleton singleton = SingletonEnum.SINGLETON_ENUM.getSingleton();
        Singleton singleton2 = SingletonEnum.SINGLETON_ENUM.getSingleton();
        System.out.println(singleton);
        System.out.println(singleton2);


        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  " + SingletonEnum.SINGLETON_ENUM.getSingleton());
            }).start();
        }

    }
}
