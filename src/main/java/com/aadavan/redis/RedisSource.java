package com.aadavan.redis;

public class RedisSource {

    private String ip;
    private int port;
    private long duration;
    private String username;
    private String password;

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
