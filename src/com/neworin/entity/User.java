package com.neworin.entity;

/**
 * Created by NewOrin Zhang on 2016/6/28.
 * Email: NewOrinZhang@Gmail.com
 */
public class User {

    private String username;
    private String userpwd;

    public User() {
        super();
    }

    public User(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                '}';
    }
}
