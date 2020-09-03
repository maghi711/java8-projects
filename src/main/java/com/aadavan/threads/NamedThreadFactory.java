package com.aadavan.threads;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    private static EnsUncaughtExceptionHandler eh = new EnsUncaughtExceptionHandler();
    private String namePrefix;
    private int idx = 0;
    private int priority = Thread.NORM_PRIORITY;

    public NamedThreadFactory(String namePrefix){
        this.namePrefix= namePrefix;
    }

    public NamedThreadFactory(String namePrefix, int priority){
        this.namePrefix= namePrefix;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, namePrefix + (idx++));//new Thread(group, target, name)
        t.setUncaughtExceptionHandler(eh);
        t.setPriority(priority);
        return t;
    }
}
