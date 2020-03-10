package com.example.skymovierater.models;

public class movie_table {

    private int mov_id;
    private String mov_title;
    private String mov_description;

    public int getMov_id() {
        return mov_id;
    }

    public void setMov_id(int mov_id) {
        this.mov_id = mov_id;
    }

    public String getMov_title() {
        return mov_title;
    }

    public void setMov_title(String mov_title) {
        this.mov_title = mov_title;
    }

    public String getMov_description() {
        return mov_description;
    }

    public void setMov_description(String mov_description) {
        this.mov_description = mov_description;
    }
}
