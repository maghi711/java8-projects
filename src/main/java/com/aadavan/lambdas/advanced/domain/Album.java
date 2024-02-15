package com.aadavan.lambdas.advanced.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

public final class Album {

    private String name;

    private List<Track> tracks;

    private List<Artist> musicians;

    public String getName() {
        return name;
    }

    public List<Artist> getMusiciansList() {
        return unmodifiableList(musicians);
    }

    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    public List<Track> getTracksList() {
        return unmodifiableList(tracks);
    }

    public Stream<Track> getTracks() {
        return tracks.stream();
    }
}
