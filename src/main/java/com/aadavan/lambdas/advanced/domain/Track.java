package com.aadavan.lambdas.advanced.domain;

public final class Track {

    private final String name;

    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public static Track createTrack(String name, int length) {
        return new Track(name, length);
    }

    public Track copy() {
        return createTrack(name, length);
    }
}
