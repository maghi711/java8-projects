package com.aadavan.completablefuture.practice1;

public class CpuCoreAndPercentToUseCalculation {

    public static void main(String[] args) {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);
        int coreCount = (availableProcessors * 85) / 100;
        System.out.println("coreCount = " + coreCount);    }
}
