package com.design.decorate;

/**
 * 具体构件角色：定义一个将要接收附加责任的类
 */
public class ConcretComponet implements Component {
    @Override
    public void doSomething() {
        System.out.println("ConcretComponet");
    }
}
