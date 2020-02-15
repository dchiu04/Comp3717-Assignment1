package com.example.comp3717_assignment1;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleArray{

    public static final ArrayList<Articles> articles = new ArrayList<Articles>();

    //Volley.newRequestQueue(MainActivity.this)
    public static void loadNewArticles(String userQuery, RequestQueue requestQueue) {

        String url_req = "https://newsapi.org/v2/everything?q=" + userQuery +
                 "&from=2020-02-14&sortBy=publishedAt&apiKey=7706477163614421b4c5d5b6b9dcf354";

        articles.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_req, new JSONObject(), (res) -> {
            System.out.println(res);
            JSONArray jsonArr = new JSONArray();
            try {
                jsonArr = res.getJSONArray("articles");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonArr); // gets all articles.
            try {

                for (int i = 0; i < 10; i++) {
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
