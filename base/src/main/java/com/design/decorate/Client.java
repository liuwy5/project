package com.design.decorate;

public class Client {
    public static void main(String[] args) {
        Component component = new ConcretComponet();
        component.doSomething();

        component = new ConcreteDecorateB(new ConcreteDecorateA(component));
        component.doSomething();
    }
}
