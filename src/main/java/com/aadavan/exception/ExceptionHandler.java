package com.aadavan.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandler {
    static Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    public static void main(String[] args) {
        logger.info("check it");
        try {
            check();
        } catch (Exception e) {
            long start = System.currentTimeMillis();
            final ExceptionDetails exceptionDetails = getExceptionDetails(e);
            long end = System.currentTimeMillis();
            logger.log(Level.SEVERE,"(end-start) = {0}" + (end - start));
            logger.log(Level.SEVERE, "Exception occurred and the message is {0} at line number {1}", new Object[]{exceptionDetails.getMessage(), exceptionDetails.getLineNumber()});
            start = System.currentTimeMillis();
            e.printStackTrace();
            end = System.currentTimeMillis();
            logger.log(Level.SEVERE,"(stackTrace) = {0}" + (end - start));
        }
    }

    public static void check() {
        logger.info("check");
        validate();
    }

    public static void validate() {
        logger.info("validate");
        /*
        try {
            int i = 1 / (1 - 1);
        } catch (Exception e) {
            long start = System.currentTimeMillis();
            final ExceptionDetails exceptionDetails = getExceptionDetails(e);
            long end = System.currentTimeMillis();
            logger.log(Level.SEVERE,"(end-start) = {0}" + (end - start));
            logger.log(Level.SEVERE, "Exception occurred and the message is {0} at line number {1}", new Object[]{exceptionDetails.getMessage(), exceptionDetails.getLineNumber()});
            start = System.currentTimeMillis();
            e.printStackTrace();
            end = System.currentTimeMillis();
            logger.log(Level.SEVERE,"(stackTrace) = {0}" + (end - start));
        }
        */
        int i = 1 / (1 - 1);
        logger.info("completed validation");
    }

    public static ExceptionDetails getExceptionDetails(Throwable e) {
        final String message = e.getMessage();
        final StackTraceElement[] stackTrace = e.getStackTrace();
        int lineNumber = 0;
        if (stackTrace != null && stackTrace.length >= 1) {
            lineNumber = stackTrace[0].getLineNumber();
        }
        ExceptionDetails exceptionDetails = new ExceptionDetails(message, lineNumber);
        return exceptionDetails;
    }
}

class ExceptionDetails {
    final private String message;
    final private int lineNumber;

    public ExceptionDetails(String message, int lineNumber) {
        this.message = message;
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getMessage() {
        return message;
    }
}
