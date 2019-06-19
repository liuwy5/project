package com.design.strategy.sortList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NameDescSort implements SortList, Comparator<Person> {
    @Override
    public void sort(List list) {
        Collections.sort(list, this);
    }

    @Override
    public int compare(Person o1, Person o2) {
        int result = o2.getName().compareTo(o1.getName());
        if (result == 0) {
            result = o1.getId() - o2.getId();
        }
        return result;
    }
}
