package com.design.decorate;

/**
 * 装饰角色：持有一个构件对象的引用，并定义一个与抽象构件接口一致的接口
 */
public class Decorate implements Component {
    protected Component component;

    public Decorate(Component component) {
        this.component = component;
    }

    public void doSomething() {
        this.component.doSomething();
    }
}
