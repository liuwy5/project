package com.type.info.nullObject;

import java.util.Arrays;
import java.util.List;

public class RemoveSnowRobot implements Robot {

    private String name;

    public RemoveSnowRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return name;
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    @Override
                    public String description() {
                        return "description1";
                    }

                    @Override
                    public void command() {
                        System.out.println("command1");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return "description2";
                    }

                    @Override
                    public void command() {
                        System.out.println("command2");
                    }
                }
        );
    }

    public static void main(String[] args) {
        Robot.Test.test(new RemoveSnowRobot("RemoveSnowRobot"));
    }
}
