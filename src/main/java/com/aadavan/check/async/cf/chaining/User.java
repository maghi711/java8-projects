package com.aadavan.check.async.cf.chaining;

public class User {

    private Long id;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
