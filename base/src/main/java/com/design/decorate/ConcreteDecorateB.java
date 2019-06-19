package com.design.decorate;

/**
 * 具体装饰角色：负责给构件对象贴上附加的责任
 */
public class ConcreteDecorateB extends Decorate {

    public ConcreteDecorateB(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        System.out.println("ConcreteDecorateB doSomething");
    }
}
