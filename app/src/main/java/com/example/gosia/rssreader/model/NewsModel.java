package com.example.gosia.rssreader.model;

import java.util.List;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsModel {

    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
