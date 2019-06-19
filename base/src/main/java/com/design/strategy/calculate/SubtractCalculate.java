package com.design.strategy.calculate;

public class SubtractCalculate implements Calculate {
    @Override
    public int cal(int x, int y) {
        return x - y;
    }
}
