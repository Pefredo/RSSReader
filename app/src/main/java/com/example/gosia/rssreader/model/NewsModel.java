package com.example.gosia.rssreader.model;

import java.util.List;

/**
 * Created by Gosia on 25.03.2017.
 */

public class NewsModel {

    private String status;
    private String source;
    private String sortBy;
    private List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public class Articles {
        List<Article> article;

        public List<Article> getArticle() {
            return article;
        }
    }
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
    }
