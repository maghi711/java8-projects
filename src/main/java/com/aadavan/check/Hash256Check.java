package com.aadavan.check;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hash256Check {
    public static void main(String[] args) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String data = "7708572244";
        byte[] encodedhash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        //System.out.println("Base 64 encoding " + new String(Base64.encode(encodedhash));
        System.out.println("encodedhash = " + bytesToHex(encodedhash));
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
