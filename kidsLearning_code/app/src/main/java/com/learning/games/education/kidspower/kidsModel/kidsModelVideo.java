package com.learning.games.education.kidspower.kidsModel;

import java.io.Serializable;

public class kidsModelVideo implements Serializable {
    String videoId;
    String videoThumb;
    String videoTitle;

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String videoId2) {
        this.videoId = videoId2;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String videoTitle2) {
        this.videoTitle = videoTitle2;
    }

    public String getVideoThumb() {
        return this.videoThumb;
    }

    public void setVideoThumb(String videoThumb2) {
        this.videoThumb = videoThumb2;
    }
}
