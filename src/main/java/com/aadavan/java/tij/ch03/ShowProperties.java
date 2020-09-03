package com.aadavan.java.tij.ch03;

public class ShowProperties {
    public static void main(String[] args) {

        //System.getProperties().list(System.out);
        final String key = "user.name";
        System.out.println(
                key + " --> " + System.getProperty(key)
        );

        final String key1 = "java.library.path";
        System.out.println(
                key1 + "" + System.getProperty(key1)
        );

    }
}
