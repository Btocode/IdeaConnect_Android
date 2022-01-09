package com.afsan.ideaconnect_android.Model;

public class EditInfoModel {
String jobTitle,programming,languageKnown,linkedIn,resume,github,bio,gender;
int age,user;

    public EditInfoModel(int user,String jobTitle, String programming, String languageKnown, String linkedIn, String resume, String github, String bio, String gender, int age) {
        this.jobTitle = jobTitle;
        this.programming = programming;
        this.languageKnown = languageKnown;
        this.linkedIn = linkedIn;
        this.resume = resume;
        this.github = github;
        this.bio = bio;
        this.gender = gender;
        this.age = age;
        this.user = user;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProgramming() {
        return programming;
    }

    public void setProgramming(String programming) {
        this.programming = programming;
    }

    public String getLanguageKnown() {
        return languageKnown;
    }

    public void setLanguageKnown(String languageKnown) {
        this.languageKnown = languageKnown;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
