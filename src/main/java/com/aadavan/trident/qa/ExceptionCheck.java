package com.aadavan.trident.qa;

public class ExceptionCheck {
    public static void main(String[] args) {
        try {
            System.out.println("Inside first try");
            try {
                System.out.println("Inside second try catch");
                persist();
            } catch (Exception e) {
                System.out.println("Exception propogated to second try catch, re throwing.");
                throw e;
            }
        } catch (Exception e) {
            System.out.println("Handling the exception");
        }
    }

    /**
     * This will throw NullPointerException
     */
    static void persist() {
        try {
            String st = null;
            st.toCharArray();
        } catch (Exception e) {
            System.out.println("Exception in persist, throwing back.");
            throw e;
        }
    }
}
