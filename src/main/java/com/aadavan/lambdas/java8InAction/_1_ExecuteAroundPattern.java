package com.aadavan.lambdas.java8InAction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/**
 *Execute around pattern:
 *
 * 
 */
public class _1_ExecuteAroundPattern {
    public static void main(String[] args) throws IOException {
        String filename = "d://tmp//snaboot_0.log";
        System.out.println(processFile(filename));

        Function<BufferedReader, String> function = (br) -> {
            try {
                return br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        };

        Function<BufferedReader, String> readTwoLines = (br) -> {
            try {
                return br.readLine() + br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        };
        System.out.println(processFile(filename, function));
        System.out.println(processFile(filename, readTwoLines));
    }

    private static String processFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return br.readLine();
        }
    }

    private static String processFile(String filename, Function<BufferedReader, String> function) throws IOException {
        System.out.println("Inside the functional interface impl");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return function.apply(br);
        }
    }
}
