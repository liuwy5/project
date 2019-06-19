package com.base.recursion;

public class Compute {
    /**
     * 阶乘
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n > 1) {
            int value = factorial(n - 1) * n;
            System.out.println(n + "! = " + value);
            return value;
        } else {
            System.out.println("1! = 1");
            return 1;
        }
    }

    /**
     * 斐波拉契数列
     * 1 1 2 3 5 8 13 21 34
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
//            System.out.print(1 + " ");
            return 1;
        } else {
            int value = fibonacci(n - 1) + fibonacci(n - 2);
//            System.out.print(value + " "); // 结果需要前两个元素 所以打印会有很多重复元素
            return value;
        }
    }

    public static void main(String[] args) {
        System.out.println("阶乘:");
        factorial(5);
        System.out.println("斐波拉契数列:");
        System.out.println(fibonacci(9));
    }
}
