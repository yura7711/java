package com.geekbrains.server.entities;

public class JwtAuthRequest {
    private String userlogin;
    private String password;

    public JwtAuthRequest() {
    }

    public JwtAuthRequest(String userlogin, String password) {
        this.setUserlogin(userlogin);
        this.setPassword(password);
    }

    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}