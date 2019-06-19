package com.sort;

public class BubbleSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }

            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[] array = {5, 3, 6, 9, 1};
        sort(array);
    }
}
