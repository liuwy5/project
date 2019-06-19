package com.collections.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest2 {
    public static void main(String[] args) {
        List<String> list = Collections.nCopies(3, "a");
//        list.add("b"); // 返回的为不可变列表，所以不能执行add操作
        System.out.println(list);

//        Collections.fill(list, "b");  // 不可变列表 不能改变元素

        List<String> list1 = new ArrayList(list);
        System.out.println(list1);
        Collections.fill(list1, "b");
        System.out.println(list1);
    }
}
