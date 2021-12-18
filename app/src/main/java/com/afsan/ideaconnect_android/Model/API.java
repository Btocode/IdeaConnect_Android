package com.afsan.ideaconnect_android.Model;

import com.google.gson.annotations.SerializedName;

public class API {
    private String ideatitle,ideatags,ideaDesc;
    private int ideaId,upVotes,downVotes;
    @SerializedName("user")
    private UserInfo userInfo;

    public API(String ideatitle, String ideatags, String ideaDesc, int ideaId, int upVotes, int downVotes, UserInfo userInfo) {
        this.ideatitle = ideatitle;
        this.ideatags = ideatags;
        this.ideaDesc = ideaDesc;
        this.ideaId = ideaId;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.userInfo = userInfo;
    }

    public String getIdeatitle() {
        return ideatitle;
    }

    public void setIdeatitle(String ideatitle) {
        this.ideatitle = ideatitle;
    }

    public String getIdeatags() {
        return ideatags;
    }

    public void setIdeatags(String ideatags) {
        this.ideatags = ideatags;
    }

    public String getIdeaDesc() {
        return ideaDesc;
    }

    public void setIdeaDesc(String ideaDesc) {
        this.ideaDesc = ideaDesc;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
