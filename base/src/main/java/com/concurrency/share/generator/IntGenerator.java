package com.concurrency.share.generator;

public abstract class IntGenerator {
    private boolean isLocked = false;

    public abstract int next();

    public void lock() {
        isLocked = true;
    }

    public boolean isLock() {
        return isLocked;
    }
}
