package com.example.comp3717_assignment1;

public class Articles {
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;


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

    public String title() {
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
