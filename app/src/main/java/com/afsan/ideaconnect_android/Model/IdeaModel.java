package com.afsan.ideaconnect_android.Model;

public class IdeaModel {
    int profile;
    String name, idea, title, tags, suggestion,upvote,downvote;



    public IdeaModel(int profile, String name, String idea, String title, String tags, String suggestion, String upvote, String downvote) {
        this.profile = profile;
        this.name = name;
        this.idea = idea;
        this.title = title;
        this.tags = tags;
        this.suggestion = suggestion;
        this.upvote = upvote;
        this.downvote = downvote;
        System.out.println(tags);
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdea() {
        return idea;
    }
    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    public String getUpvote() {
        return upvote;
    }

    public void setUpvote(String upvote) {
        this.upvote = upvote;
    }

    public String getDownvote() {
        return downvote;
    }

    public void setDownvote(String downvote) {
        this.downvote = downvote;
    }
}
