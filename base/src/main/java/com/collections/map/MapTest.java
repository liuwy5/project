package com.collections.map;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("a", "aaa");
        map.put("b", "bbbb");
        map.put("d", "bbbb");
        map.put("c", "ccc");
        map.put("e", "eee");
        System.out.println(map);

        System.out.println("-------------------Map.Entry遍历-------------------");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("-------------------对key或value的列表做修改-------------------");

        // set remove
        System.out.println("set remove a: ");
        Set<String> set = map.keySet();
        set.remove("a");
        System.out.println(map);

        System.out.println("for循环remove 会出现java.util.ConcurrentModificationException: ");
//        for (String key : set) {
//            if ("c".equals(key)) {
//                set.remove(key);
//            }
//        }

        System.out.println("set remove e: ");
        Iterator<String> iter = set.iterator();
        // set.removeIf("e"::equals); 1.8
        while (iter.hasNext()) {
            String key = iter.next();
            if ("e".equals(key)) {
                iter.remove();
            }
        }
        System.out.println(set);
        System.out.println(map);

        Collection<String> collection = map.values();
        // 不能在foreach中remove，会出现java.util.ConcurrentModificationException
//        for (String value : collection) {
//            if ("bbbb".equals(value)) {
//                collection.remove(value);
//            }
//        }

        System.out.println("value remove bbbb:");
        collection.remove("bbbb");
        System.out.println(map);

        System.out.println("value iterator remove bbbb: ");
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("bbbb".equals(value)) {
                iterator.remove();
                break;
            }
        }
        System.out.println(map);
    }
}
