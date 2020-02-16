package com.example.comp3717_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView article_source;
    TextView article_author;
    TextView article_title;
    TextView article_description;
    TextView article_URL;
    ImageView img;
    TextView article_publish_time;
    TextView article_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);

        int i = (int) getIntent().getExtras().get("index");

        article_source = findViewById(R.id.article_source);
        article_source.setText("Source: " + ArticleArray.articles.get(i).getSource());

        article_author = findViewById(R.id.article_author);
        article_author.setText("Author: " + ArticleArray.articles.get(i).getAuthor());

        article_title = findViewById(R.id.article_title);
        article_title.setText("Title: " + ArticleArray.articles.get(i).title());

        article_description = findViewById(R.id.article_description);
        article_description.setText("Description: " + ArticleArray.articles.get(i).getDescription());

        article_URL= findViewById(R.id.article_URL);
        article_URL.setText("URL: " + ArticleArray.articles.get(i).getUrl());

        img = findViewById(R.id.img);
        String url_to_img = ArticleArray.articles.get(i).getUrlToImage();

        Picasso.with(DetailsActivity.this)
                .load(url_to_img)
                .into(img);

        article_publish_time = findViewById(R.id.article_publish);
        article_publish_time.setText("Publish time: " + ArticleArray.articles.get(i).getPublishedAt());

        article_content = findViewById(R.id.article_content);
        article_content.setText("Content: " + ArticleArray.articles.get(i).getContent());
    }
}
