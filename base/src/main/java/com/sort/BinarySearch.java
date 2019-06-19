package com.sort;

public class BinarySearch {
    public static int find(int[] data, int value) {
        int start = 0;
        int end = data.length - 1;
        int index;
        while (start <= end) {
            index = (start + end) / 2;
            System.out.println(start + " " + end + " " + index);
            if (value == data[index]) {
                return index;
            } else if (value > data[index]) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 5, 23, 45, 67, 98};
        System.out.println(find(data, 67));
    }
}
