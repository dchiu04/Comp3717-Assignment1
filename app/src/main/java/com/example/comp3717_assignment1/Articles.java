package com.example.comp3717_assignment1;

public class Articles {
    String source;
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;

    public Articles(String src, String auth, String titl, String desc, String link,
                   String imgUrl, String date, String cont) {
        source = src;
        author = auth;
        title = titl;
        description = desc;
        url = link;
        urlToImage = imgUrl;
        publishedAt = date;
        content = cont;
    }

    public String getSource() {
        return source;
    }

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

    public String getContent() {
        return content;
    }
}
