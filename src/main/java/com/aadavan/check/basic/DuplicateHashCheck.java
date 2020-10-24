package com.aadavan.check.basic;

import com.aadavan.trident.Base64;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class DuplicateHashCheck {
    private static MessageDigest messageDigest = null;

    public static void main(String[] args) throws Exception {
        List<String> cardNos = Arrays.asList("4378399635663633", "4378399635663635");
        for (String cardNo: cardNos) {
            getSHA256HashValue(cardNo, 2);
        }
    }

    public static String getSHA256HashValue(String rawValue, int hashCount) throws Exception {
        messageDigest = MessageDigest.getInstance("SHA-256");
        String hashedData = null;
        hashedData = rawValue;
        for(int i = 1; i<=hashCount; i++){
            byte dataProcessed[] = messageDigest.digest(hashedData.getBytes("UTF-8"));
            hashedData = new String(Base64.encode(dataProcessed));
            System.out.println("HASHEDDATA = " + hashedData);
        }
        return hashedData;
    }
}
