package com.design.observe.one;

import java.util.ArrayList;
import java.util.List;

public class ConcretObserved implements Observed {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notify(Object o) {
        for (Observer observer : observerList) {
            observer.update(o);
        }
    }
}
