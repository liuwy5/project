package com.design.observe.one;

public class Test {
    public static void main(String[] args) {
        Observed observed = new ConcretObserved();

        Observer observer1 = new ConcretObserver("observer1");
        Observer observer2 = new ConcretObserver("observer2");

        observed.addObserver(observer1);
        observed.addObserver(observer2);

        observed.notify("123");

        observed.removeObserver(observer1);

        observed.notify("12");
    }
}
