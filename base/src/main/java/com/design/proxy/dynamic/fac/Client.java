package com.design.proxy.dynamic.fac;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List list = (List) new ProxyHandler(new ArrayList<>()).factory();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list);

        list.remove(0);
        System.out.println(list);
    }
}
