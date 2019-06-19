package com.collections.iterable;

import java.util.*;

public class ReversibleArray<T> extends ArrayList<T> {
    public ReversibleArray(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public T next() {
                        return get(current--);
                    }
                };
            }
        };
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        ReversibleArray<String> reversibleArray = new ReversibleArray<>(list);
        for (String s : reversibleArray) {
            System.out.println(s);
        }

        for (String s : reversibleArray.reversed()) {
            System.out.println(s);
        }
    }
}
