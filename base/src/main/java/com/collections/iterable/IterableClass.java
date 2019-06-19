package com.collections.iterable;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {
    protected String[] values = "i f df fd f k l".split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public String next() {
                return values[index++];
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println(s);
        }
    }
}
