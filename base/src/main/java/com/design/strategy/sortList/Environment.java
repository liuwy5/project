package com.design.strategy.sortList;

import java.util.List;

public class Environment {
    private SortList sortList;

    public void setSortList(SortList sortList) {
        this.sortList = sortList;
    }

    public void sort(List list) {
        this.sortList.sort(list);
    }
}
