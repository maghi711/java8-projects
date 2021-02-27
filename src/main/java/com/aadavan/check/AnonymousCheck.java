package com.aadavan.check;

public class AnonymousCheck {

    public final int value = 4;

    public static void main(String[] args) {
        AnonymousCheck check = new AnonymousCheck();
        check.doIt();
    }

    private void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
}
