package com.afsan.ideaconnect_android.Model;

public class CreatePostModel {
    private String ideaTitle,ideaDesc,ideaTags;
    private int author;

    public CreatePostModel(String ideaTitle, String ideaDesc, String ideaTags, int author) {
        this.ideaTitle = ideaTitle;
        this.ideaDesc = ideaDesc;
        this.ideaTags = ideaTags;
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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
