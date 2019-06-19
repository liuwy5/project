package com.design.strategy.sortList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NameAscSort implements SortList, Comparator<Person> {
    @Override
    public void sort(List list) {
        Collections.sort(list, this);
    }

    @Override
    public int compare(Person o1, Person o2) {
        int result = o1.getName().compareTo(o2.getName());
        if (result == 0) {
            result = o1.getId() - o2.getId();
        }
        return result;
    }
}
