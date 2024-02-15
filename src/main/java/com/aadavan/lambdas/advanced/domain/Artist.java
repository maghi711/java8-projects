package com.aadavan.lambdas.advanced.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * An individual or group who creates music.
 * name: The name of the artist (e.g., “The Beatles”)
 * members: A set of other artists who comprise this group (e.g., “John Lennon”);
 * this field might be empty
 * origin: The primary location of origin of the group (e.g., “Liverpool”).
 */
public final class Artist {

    private String name;

    private List<Artist> members;

    private String nationality;

    public Artist(String name, String nationality) {
        this(name, Collections.emptyList(), nationality);
    }

    public Artist(String name, List<Artist> members, String nationality) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        Objects.requireNonNull(nationality);
        this.name = name;
        this.members = members;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public List<Artist> getMembers() {
        return members;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isSolo() {
        return members.isEmpty();
    }

    @Override
    public String toString() {
        return getName();
    }

    public static Artist create(String name, List<Artist> members, String nationality) {
        return new Artist(name, members, nationality);
    }

    public Artist copy() {
        final List<Artist> collect = getMembers().stream().map(Artist::copy).collect(Collectors.toList());
        return create(name, collect, nationality);
    }
}
