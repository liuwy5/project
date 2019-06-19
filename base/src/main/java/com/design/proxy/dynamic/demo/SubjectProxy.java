package com.design.proxy.dynamic.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectProxy implements InvocationHandler {

    private Object target;

    public SubjectProxy(Object target) {
        this.target = target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy subject is : " + proxy.getClass());
        System.out.println("method is : " + method.getName());
        System.out.println("real subject: " + target.getClass());
        return method.invoke(target, args);
    }
}
