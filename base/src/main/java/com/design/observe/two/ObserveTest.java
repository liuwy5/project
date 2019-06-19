package com.design.observe.two;

import java.util.Observable;
import java.util.Observer;

class ConObserved extends Observable {
    public void change(Integer number) {
        while (number >= 0) {
            setChanged();
            notifyObservers(number);

            number--;
        }
    }
}

class ConObserver1 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ConObserver1: " + arg);
    }
}

class ConObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Integer integer = (Integer) arg;
        if (integer <= 5) {
            System.out.println("ConObserver2: " + arg);
        }
    }
}

public class ObserveTest {
    public static void main(String[] args) {
        ConObserved conObserved = new ConObserved();

        Observer observer1 = new ConObserver1();
        Observer observer2 = new ConObserver2();

        conObserved.addObserver(observer1);
        conObserved.addObserver(observer2);

        conObserved.change(10);
    }
}
