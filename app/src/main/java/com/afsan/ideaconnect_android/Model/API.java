package com.afsan.ideaconnect_android.Model;

import com.google.gson.annotations.SerializedName;

/*
{
        "ideaId": 42,
        "ideaTitle": "Dynamic Operations Designer",
        "ideaDesc": "Aut et minima.",
        "ideaTags": "Accusantium perspiciatis minus sed fuga totam excepturi.",
        "author": 20,
        "upvotes": [
            15
        ],
        "downvotes": [
            20,
            22
        ],
        "suggestions": [],
        "postingTime": "2021-12-30T06:57:25.100366Z",
        "voteCounter": -1,
        "first_name": "kamal",
        "last_name": "Hossain"
    },

 */



public class API {
    int profile, ideaId, voteCounter, author;
    String ideaTitle, ideaDesc, ideaTags, last_name, first_name;
    int[] upvotes;
    int[] downvotes;
    int[] suggestions;


    public API(int profile, int ideaId, int voteCounter, int author, String ideaTitle, String ideaDesc, String ideaTags, String last_name, String first_name, int[] upvotes, int[] downvotes, int[] suggestions) {
        this.profile = profile;
        this.ideaId = ideaId;
        this.voteCounter = voteCounter;
        this.author = author;
        this.ideaTitle = ideaTitle;
        this.ideaDesc = ideaDesc;
        this.ideaTags = ideaTags;
        this.last_name = last_name;
        this.first_name = first_name;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.suggestions = suggestions;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(int voteCounter) {
        this.voteCounter = voteCounter;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getIdeaDesc() {
        return ideaDesc;
    }

    public void setIdeaDesc(String ideaDesc) {
        this.ideaDesc = ideaDesc;
    }

    public String getIdeaTags() {
        return ideaTags;
    }

    public void setIdeaTags(String ideaTags) {
        this.ideaTags = ideaTags;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int[] getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int[] upvotes) {
        this.upvotes = upvotes;
    }

    public int[] getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int[] downvotes) {
        this.downvotes = downvotes;
    }

    public int[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(int[] suggestions) {
        this.suggestions = suggestions;
    }
}