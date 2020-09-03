package com.aadavan.trident.qa;

import java.io.RandomAccessFile;

public class FileWritingRandomAccessFile {

    public static void main(String[] args) throws Exception {

        final String filename = "D:/tmp/logfile.txt";
        display(filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw");
        int count = 0;
        String value = null;
        long filePointer = 1;
        while ((value = randomAccessFile.readLine()) != null) {
            if (value.length() == 0) {
                filePointer = randomAccessFile.getFilePointer();
                System.out.println("filePointer = " + filePointer);
                break;
            }
            count++;
        }
        System.out.println("Number of lines in the file is " + count);
        randomAccessFile.seek((filePointer-1));
        randomAccessFile.writeBytes("I love aadavan\nDone");
        randomAccessFile.close();
        display(filename);
    }

    private static void display(String filename) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw");
        for (int i = 0; i < 4; i++) {
            System.out.println("i = " + i + " values " + randomAccessFile.readLine());
        }
        randomAccessFile.close();
    }

}
