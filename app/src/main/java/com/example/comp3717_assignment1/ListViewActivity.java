package com.example.comp3717_assignment1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListViewActivity extends AppCompatActivity {
    private static final String API_KEY = "7706477163614421b4c5d5b6b9dcf354";
    String DATE = "yyy-MM-dd";
    static ListView listView;
    String keyword;
    static ArrayAdapter<String> adapter;
    static ArrayList<String> arr;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        intent = getIntent();
        arr = new ArrayList();
        RequestQueue requestQueue = Volley.newRequestQueue(ListViewActivity.this);

        //Not needed?
        //adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, arr);

        keyword = intent.getStringExtra("keyword");
        System.out.println("This is the keyword: " + keyword);
        listView = findViewById(R.id.list);
        loadNewArticles(keyword, requestQueue, ListViewActivity.this);
    }

    public static void printAllArticles() {
        //Display all lists here!
        for (int i = 0; i < ArticleArray.articles.size(); i++) {
            arr.add(ArticleArray.articles.get(i).title());
            System.out.println("String arraylist holds: " + arr.get(i));

        }
        listView.setAdapter(adapter);
    }

    public static void loadNewArticles(String userQuery, RequestQueue requestQueue, Context cv) {

        String url_req = "https://newsapi.org/v2/everything?q=" + userQuery
                + "&from=2020-02-14&sortBy=publishedAt&apiKey=" + API_KEY;

        ArticleArray.articles.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_req, new JSONObject(), (res) -> {
            JSONArray jsonArr = new JSONArray();
            try {
                jsonArr = res.getJSONArray("articles");
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
                    ArticleArray.articles.add(art);
                    printAllArticles();
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


