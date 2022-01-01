package com.afsan.ideaconnect_android.Model;

public class LoginModel {
    String username,password;
    String access, refresh;


    public LoginModel(String username, String password, String access, String refresh) {
        this.username = username;
        this.password = password;
        this.access = access;
        this.refresh = refresh;
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

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
