package com.afsan.ideaconnect_android.Model;

public class NotificationModel {
    int dp;
    String noti_name, noti_content,noti_time;

    public NotificationModel(int dp, String noti_name, String noti_content, String noti_time) {
        this.dp = dp;
        this.noti_name = noti_name;
        this.noti_content = noti_content;
        this.noti_time = noti_time;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getNoti_name() {
        return noti_name;
    }

    public void setNoti_name(String noti_name) {
        this.noti_name = noti_name;
    }

    public String getNoti_content() {
        return noti_content;
    }

    public void setNoti_content(String noti_content) {
        this.noti_content = noti_content;
    }

    public String getNoti_time() {
        return noti_time;
    }

    public void setNoti_time(String noti_time) {
        this.noti_time = noti_time;
    }
}
