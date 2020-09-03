package com.aadavan.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EnsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger logger = Logger.getLogger(Thread.UncaughtExceptionHandler.class.getName());

    public void uncaughtException(Thread t, Throwable e) {
        logger.log(Level.ALL,"uncaught exp in thread "  +t, e);
    }

}