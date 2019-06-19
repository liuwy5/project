package com.collections.list;

public class NodeTest2 {
    public static void main(String[] args) {
        // 双向循环链表
        Node2 node1 = new Node2("node1");
        Node2 node2 = new Node2("node2");
        Node2 node3 = new Node2("node3");

        node1.previous = node3;
        node1.next = node2;
        node2.previous = node1;
        node2.next = node3;
        node3.previous = node2;
        node3.next = node1;

        System.out.println(node1.next.next.data);
        System.out.println(node1.previous.data);

        // 1和2之家插入4
        Node2 node4 = new Node2("node4");

        node1.next.previous = node4;
        node4.next = node1.next;
        node1.next = node4;
        node4.previous = node1;

        System.out.println("--------------");
        System.out.println(node1.next.data);
        System.out.println(node1.next.next.data);

        // 删除node4
        node1.next = node2;
        node2.previous = node1;
        node4.previous = null;
        node4.next = null;
        System.out.println("----------------");
        System.out.println(node1.next.data);
    }
}

class Node2 {
    Node2 previous;

    String data;

    Node2 next;

    public Node2(String data) {
        this.data = data;
    }
}
