package com.example.skymovierater.models;

public class Movie {

    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private  String streamLink;
    private int coverPhoto;


    public Movie(String title, int thumbnail, int coverPhoto, String description) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.description = description;
    }

    public Movie(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public Movie(String title, String description, int thumbnail, String studio, String rating, String streamLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamLink = streamLink;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDecsription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamLink() {
        return streamLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDecsription(String decsription) {
        this.description = decsription;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamLink(String streamLink) {
        this.streamLink = streamLink;
    }
}
