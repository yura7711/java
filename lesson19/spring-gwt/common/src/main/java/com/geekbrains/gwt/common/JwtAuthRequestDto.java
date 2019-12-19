package com.geekbrains.gwt.common;

public class JwtAuthRequestDto {
    private String userlogin;
    private String password;

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

    public JwtAuthRequestDto() {
    }

    public JwtAuthRequestDto(String userlogin, String password) {
        this.userlogin = userlogin;
        this.password = password;
    }
}