package com.generic.simple;

public class LinkedStack<T> {
    private static class Node<U> {
        private U item;

        private Node<U> next;

        public Node() {
        }

        public Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        public boolean end() {
            return item == null && next == null;
        }
    }

    public Node<T> top = new Node<>();

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (String s : "让 明天 有 梦 做 让 故事 有 然后".split(" ")) {
            stack.push(s);
        }

        String s;
        while ((s = stack.pop()) != null) {
            System.out.println(s);
        }
    }
}
