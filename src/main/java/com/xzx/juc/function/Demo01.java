package com.xzx.juc.function;

import java.util.function.Function;

/**
 * 函数型接口
 *
 * @author 谢子轩
 * @date 2023/03/15 16/16
 */
public class Demo01 {

    public static void main(String[] args) {
        Function function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };


        Function lamFun = (str) -> {
            return str;
        };

        System.out.println(function.apply("123"));
        System.out.println(lamFun.apply("123"));


    }

}
