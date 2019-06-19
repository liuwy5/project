package com.design.singleton;

public class Singleton {

}

/**
 * normal
 */
class SingletonNormal {
    /**
     * 因为方法为static方法，所以为static域
     */
    private static SingletonNormal singleton;

    private SingletonNormal() {}

    /**
     * 单例 所以外面不能生成对象 所以为static方法
     */
    public static SingletonNormal getInstance() {
        if (singleton == null) {
            singleton = new SingletonNormal();
        }

        return singleton;
    }
}

/**
 * volatile double check
 */
class SingleVolatile {
    private volatile static SingleVolatile singleVolatile = null;

    private SingleVolatile() {}

    public static SingleVolatile getInstance() {
        if (singleVolatile == null) {
            synchronized (SingleVolatile.class) {
                if (singleVolatile == null) {
                    singleVolatile = new SingleVolatile();
                }
            }
        }
        return singleVolatile;
    }
}

/**
 * 内部类 懒加载
 */
class SingleInnerClass {
    private SingleInnerClass() {}

    public static class Holder {
        static SingleInnerClass instance = new SingleInnerClass();
    }

    public static SingleInnerClass getInstance() {
        return Holder.instance;
    }
}
