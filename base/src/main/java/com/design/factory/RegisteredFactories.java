package com.design.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.randowObject());
        }
    }
}

interface Factory<T> {
    T create();
}

class Part {
    public static List<Factory<? extends Part>> factories = new ArrayList<>();

    static {
        factories.add(new FuelFilter.Factory());
        factories.add(new AirFilter.Factory());
        factories.add(new FanBelt.Factory());
    }

    static Random random = new Random(47);

    static Part randowObject() {
        int index = random.nextInt(factories.size());
        return factories.get(index).create();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Filter extends Part {

}

class FuelFilter extends Filter {
    static class Factory implements com.design.factory.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter {
    static class Factory implements com.design.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    static class Factory implements com.design.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}