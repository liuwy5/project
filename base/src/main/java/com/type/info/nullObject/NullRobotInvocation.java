package com.type.info.nullObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class NullRobotInvocation implements InvocationHandler {
    private String nullName;

    private Robot proxied = new NullRobot();

    public NullRobotInvocation(Class clazz) {
        nullName = clazz.getSimpleName() + " NullRobot";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }

    private class NullRobot implements Null, Robot {
        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }
}
