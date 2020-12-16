package com.aadavan.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReadCheck {
    public static void main(String[] args) {
        int i = 10;
        String st = "name " +  i * 2 + "is ";
        System.out.println("st = " + st);
    }

    void oldLogic() {
        StringBuffer stBuffer = new StringBuffer();
        try {
            File file = new File("D:\\trident_1.4.5\\issues\\oct-20\\read_redis_contents.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                stBuffer.append(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(stBuffer);

    }
}
