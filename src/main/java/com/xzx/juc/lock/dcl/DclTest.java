package com.xzx.juc.lock.dcl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author 谢子轩
 * @date 2023/03/18 16/37
 */
public class DclTest {

    private static volatile DclTest dclSingleton;

    private static boolean flag = false;

    public DclTest() {
        if (flag == false) {
            flag = true;
        } else {
            throw new RuntimeException("禁止使用反射获取");
        }
    }

    public static DclTest getInstance() {
        if (dclSingleton == null) {
            synchronized (DclTest.class) {
                if (dclSingleton == null) {
                    dclSingleton = new DclTest();
                }
            }
        }
        return dclSingleton;
    }

    public static void main(String[] args) throws Exception {
        Constructor<DclTest> declaredConstructor = DclTest.class.getDeclaredConstructor();

        DclTest dclTest = declaredConstructor.newInstance();
        System.out.println(dclTest);
        Field flag = DclTest.class.getDeclaredField("flag");
        flag.setAccessible(true);
        flag.set(dclTest,false);
        DclTest instance = declaredConstructor.newInstance();
        System.out.println(instance);

    }

}
