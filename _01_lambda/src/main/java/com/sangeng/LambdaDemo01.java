package com.sangeng;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class LambdaDemo01 {
    /*public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新的线程中run方法被执行了");
            }
        }).start();

    }*/

    public static void main(String[] args) {
//        demo01

        /*new Thread(() -> {
                System.out.println("新的线程中run方法被执行了");
        }).start();*/
        /**
         * demo02
         */
        /*int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });*/
       /* int i = calculateNum((int left, int right) ->{
            return left+right;
        });

        System.out.println(i);*/

        // demo3
        /*printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value%2==0;
            }
        });*/
//        printNum((int value) ->{
//            return value%2==0;
//        });
/**
 * demo04
 */

//        Integer integer = typeConver(s -> {
//            return Integer.valueOf(s);
//        });
//        System.out.println(integer);
//
//    }
//        demo5
        foreachArr(value -> {
            if (value % 2 == 0) {
                System.out.println(value);
            }
        });

    }

    public static <R> R typeConver(Function<String, R> function) {
        String str = "1235";
        R result = function.apply(str);
        return result;
    }


    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }
}
