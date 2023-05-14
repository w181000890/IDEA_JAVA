package com.sangeng;

import java.util.function.IntBinaryOperator;
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
        printNum((int value) ->{
            return value%2==0;
        });


    }

    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }




    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }





}
