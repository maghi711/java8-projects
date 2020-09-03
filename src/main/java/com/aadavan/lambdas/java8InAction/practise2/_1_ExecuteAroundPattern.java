package com.aadavan.lambdas.java8InAction.practise2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _1_ExecuteAroundPattern {

    public static void main(String[] args) throws Exception {
        String oneLine = processFile("D:\\tmp\\test.gvim", (br) -> br.readLine());
        System.out.println("processFile() = " + oneLine);
        String twoLine = processFile("D:\\tmp\\test.gvim", (br) -> br.readLine() + "\n" + br.readLine());
        System.out.println("processFile() = " + twoLine);
    }

    public static String processFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return br.readLine();
        }
    }

    public static String processFile(String filename, BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return processor.process(br);
        }
    }

}
