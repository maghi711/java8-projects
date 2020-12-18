package com.aadavan.completablefuture.chaining;

public class User {
    private Long id;
    private String name;

    public User(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
