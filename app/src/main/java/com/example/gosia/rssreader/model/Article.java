package com.example.gosia.rssreader.model;

/**
 * Created by archi on 08/04/2017.
 */

public class Article {
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}