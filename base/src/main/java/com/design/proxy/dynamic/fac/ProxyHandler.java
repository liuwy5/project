package com.design.proxy.dynamic.fac;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    public Object factory() {

//        Class[] clazz = object.getClass().getInterfaces();
//        for (Class claz : clazz) {
//            System.out.println(claz.getName());
//        }
        Class c = object.getClass();
        return Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), new ProxyHandler(object));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args != null) {
            for (Object o : args) {
                System.out.println("param: " + o);
            }
        }

        return method.invoke(object, args);
    }
}
