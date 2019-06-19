package com.design.strategy.calculate;

public class Client {
    public static void main(String[] args) {
        int x = 5;
        int y = 3;

        Calculate calculate = new AddCalculate();
        Environment environment = new Environment(calculate);
        System.out.println(environment.cal(x, y));

        environment.setCalculate(new SubtractCalculate());
        System.out.println(environment.cal(x, y));

        environment.setCalculate(new MultiplyCalculate());
        System.out.println(environment.cal(x, y));

        environment.setCalculate(new DivideCalculate());
        System.out.println(environment.cal(x, y));
    }
}
