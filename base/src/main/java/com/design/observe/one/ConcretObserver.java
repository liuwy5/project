package com.design.observe.one;

public class ConcretObserver implements Observer {

    private String name;

    public ConcretObserver(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConcretObserver{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void update(Object object) {
        System.out.println(this + " observed " + object);
    }
}
