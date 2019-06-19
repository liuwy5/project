package com.design.strategy.calculate;

public class DivideCalculate implements Calculate {
    @Override
    public int cal(int x, int y) {
        return x / y;
    }
}
