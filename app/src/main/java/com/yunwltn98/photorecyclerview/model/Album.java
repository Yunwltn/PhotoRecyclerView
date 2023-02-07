package com.yunwltn98.photorecyclerview.model;

import java.io.Serializable;

public class Album implements Serializable {

    public int albumId;
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;

    public Album(){

    }

    public Album(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
