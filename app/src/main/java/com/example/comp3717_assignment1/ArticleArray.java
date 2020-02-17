package com.example.comp3717_assignment1;
import android.content.Context;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleArray{

    public static final ArrayList<Articles> articles = new ArrayList<Articles>();

    public static void loadArticles(String userQuery, RequestQueue requestQueue, Context cont) {

        String URL = "https://newsapi.org/v2/everything?q=" + userQuery
                + "&from=" + MainActivity.formattedDate + "&sortBy=publishedAt&apiKey=" + MainActivity.API;
        articles.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, new JSONObject(), (res) -> {

            JSONArray jsonArr = new JSONArray();
            try {
                jsonArr = res.getJSONArray("articles");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                for (int i = 0; i < 20; i++) {
                    String source = jsonArr.getJSONObject(i).getJSONObject("source").getString("name"); // gets sources
                    String author = jsonArr.getJSONObject(i).getString("author"); // gets authors
                    String title = jsonArr.getJSONObject(i).getString("title"); // gets title
                    String description = jsonArr.getJSONObject(i).getString("description");
                    String url = jsonArr.getJSONObject(i).getString("url");
                    String urlToImage = jsonArr.getJSONObject(i).getString("urlToImage");
                    String publishedAt = jsonArr.getJSONObject(i).getString("publishedAt");
                    String content = jsonArr.getJSONObject(i).getString("content");
                    Articles art = new Articles(source, author, title, description, url, urlToImage, publishedAt,
                            content);
                    articles.add(art);
                }
                Intent i = new Intent(cont, ListViewActivity.class);
                cont.startActivity(i);
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
