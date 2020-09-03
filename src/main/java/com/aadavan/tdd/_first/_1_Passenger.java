package com.aadavan.tdd._first;

public class _1_Passenger {

    private String name;
    private boolean vip;

    public _1_Passenger(String n, boolean v) {
        this.name = n;
        this.vip = v;
    }

    public String getName() {
        return name;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_1_Passenger{");
        sb.append("name='").append(name).append('\'');
        sb.append(", vip=").append(vip);
        sb.append('}');
        return sb.toString();
    }
}
