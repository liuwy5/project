package com.collections.iterator;

import java.util.Iterator;

public class NonCollectionSequence extends PetSequence{
    public Iterator iterator() {
        return new Iterator() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < strings.length;
            }

            @Override
            public Object next() {
                return strings[index++];
            }
        };
    }

    public static void main(String[] args) {
        NonCollectionSequence sequence = new NonCollectionSequence();
        Iterator iterator = sequence.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class PetSequence {
    protected String[] strings = {"a", "b", "c"};
}

