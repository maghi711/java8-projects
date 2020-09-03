package com.aadavan.trident;

import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

public class DuplicateHash {
    private static MessageDigest messageDigest = null;
    public static void main(String[] args) throws Exception {
        String[] cardNos = {"4382459635589498", "4382459635540582", "4382459635580985", "4382459635598757"};

        for (String cardNo: cardNos) {
            System.out.println("Card: " + cardNo);
            getSHA512HashValue(cardNo, 2);
        }
        TimeUnit.DAYS.sleep(1);
    }

    public static String getSHA512HashValue(String rawValue, int hashCount) throws Exception {
        messageDigest = MessageDigest.getInstance("SHA-512");
        if (messageDigest == null) {
        }
        else {
            System.out.println("ELSE ---> messageDigest = " + messageDigest);
        }
        System.out.println("messageDigest = " + messageDigest);
        String hashedData = null;
        hashedData = rawValue;
        for(int i = 1; i<=hashCount; i++){
            byte dataProcessed[] = messageDigest.digest(hashedData.getBytes("UTF-8"));
            hashedData = new String(Base64.encode(dataProcessed));
            System.out.println("RAWVALUE = [" + rawValue + "] HASHEDDATA = " + hashedData);
        }
        return hashedData;
    }
}
