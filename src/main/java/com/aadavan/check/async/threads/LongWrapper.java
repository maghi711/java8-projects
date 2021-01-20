package com.aadavan.check.async.threads;

public class LongWrapper {

    private Object key = new Object();
    private long l;

    public LongWrapper(long l) {
        this.l = l;
    }

    public long geLongValue() {
        return l;
    }

    public void incrementByOne() {
        synchronized (key) {
            l = l + 1;
        }
    }
}
