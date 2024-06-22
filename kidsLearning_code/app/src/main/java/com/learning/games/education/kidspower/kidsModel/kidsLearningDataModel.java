package com.learning.games.education.kidspower.kidsModel;

public class kidsLearningDataModel {
    public int image;
    boolean isTrue = false;
    public String showTitle;
    public String speakTitle;

    public boolean isTrue() {
        return this.isTrue;
    }

    public void setTrue(boolean aTrue) {
        this.isTrue = aTrue;
    }

    public kidsLearningDataModel(int i, String str, String str2) {
        this.speakTitle = str;
        this.showTitle = str2;
        this.image = i;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }

    public String getSpeakTitle() {
        return this.speakTitle;
    }

    public void setSpeakTitle(String str) {
        this.speakTitle = str;
    }

    public String getShowTitle() {
        return this.showTitle;
    }

    public void setShowTitle(String str) {
        this.showTitle = str;
    }
}
