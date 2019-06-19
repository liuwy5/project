package com.collections.iterable;

import java.util.*;

public class MultiIterableClass extends IterableClass {
    public Iterable reversed() {
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new Iterator() {
                    private int current = values.length - 1;

                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public Object next() {
                        return values[current--];
                    }
                };
            }
        };
    }

    public Iterable random() {
        return new Iterable() {
            @Override
            public Iterator iterator() {
                List list = new ArrayList();
                Collections.addAll(list, values);
                Collections.shuffle(list, new Random(47));
                return list.iterator();
            }
        };
    }

    public static void main(String[] args) {
        MultiIterableClass multiIterableClass = new MultiIterableClass();
        for (String s : multiIterableClass) {
            System.out.println(s);
        }
        System.out.println();

        for (Object s : multiIterableClass.reversed()) {
            System.out.println(s);
        }
        System.out.println();

        for (Object s : multiIterableClass.random()) {
            System.out.println(s);
        }
    }
}
