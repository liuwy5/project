package com.type.info.nullObject;

import java.lang.reflect.Proxy;

public class NullRobot {

    static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(Robot.class.getClassLoader(), new Class[]{Robot.class},
                new NullRobotInvocation(type));
    }

    public static void main(String[] args) {
        Robot.Test.test(new RemoveSnowRobot("RemoveSnowRobot"));

        Robot.Test.test(newNullRobot(RemoveSnowRobot.class));
    }
}
