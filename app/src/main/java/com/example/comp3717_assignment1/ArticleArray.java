package com.example.comp3717_assignment1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleArray{

    public static final ArrayList<Articles> articles = new ArrayList<Articles>();

    //Volley.newRequestQueue(MainActivity.this)
    public static void loadNewArticles(String userQuery, RequestQueue requestQueue) {

        String req = "https://newsapi.org/v2/everything?q=" + userQuery + "&https://learn.bcit.ca/d2l/home/610176"
                + "from=2020-01-14&sortBy=publishedAt&apiKey=5805e26415354f06a8b346006b2a2226";

        articles.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, req, new JSONObject(), (res) -> {
            System.out.println(res);
            JSONArray ourShit = new JSONArray();
            try {
                ourShit = res.getJSONArray("articles");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(ourShit); // gets all articles.
            try {

                for (int i = 0; i < 10; i++) {
                    String source = ourShit.getJSONObject(i).getJSONObject("source").getString("name"); // gets sources
//                    System.out.println(source);
                    String author = ourShit.getJSONObject(i).getString("author"); // gets authors
//                    System.out.println(author);
                    String title = ourShit.getJSONObject(i).getString("title"); // gets title
//                    System.out.println(title);
                    String description = ourShit.getJSONObject(i).getString("description");
//                    System.out.println(description);
                    String url = ourShit.getJSONObject(i).getString("url");
//                    System.out.println(url);
                    String urlToImage = ourShit.getJSONObject(i).getString("urlToImage");
//                    System.out.println(urlToImage);
                    String publishedAt = ourShit.getJSONObject(i).getString("publishedAt");
//                    System.out.println(publishedAt);
                    String content = ourShit.getJSONObject(i).getString("content");
//                    System.out.println(content);
                    Articles art = new Articles(source, author, title, description, url, urlToImage, publishedAt,
                            content);
                    articles.add(art);
                    System.out.println("added article #" + (i + 1));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            requestQueue.stop();
        }, (error) -> {
            error.printStackTrace();
            requestQueue.stop();
        });

        requestQueue.add(request);

    }
}
