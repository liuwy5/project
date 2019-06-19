package com.type.info.nullObject;

import java.util.List;

public interface Robot {
    String name();

    String model();

    List<Operation> operations();

    static class Test {
        public static void test(Robot robot) {
            System.out.println("================================================");
            if (Null.class.isInstance(robot)) {
                System.out.println("Null robot");
            }
            System.out.println("name: " + robot.name() + ", model: " + robot.model());
            for (Operation operation : robot.operations()) {
                System.out.println("description: " + operation.description());
                operation.command();
            }
        }
    }
}
