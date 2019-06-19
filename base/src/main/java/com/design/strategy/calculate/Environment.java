package com.design.strategy.calculate;

public class Environment {
    private Calculate calculate;

    public Environment(Calculate calculate) {
        this.calculate = calculate;
    }

    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    public int cal(int x, int y) {
        return calculate.cal(x, y);
    }
}
