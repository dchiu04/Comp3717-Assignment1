package com.example.comp3717_assignment1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static final ArrayList<Articles> articles = new ArrayList<Articles>();
    Button button;
    EditText input;
    TextView title;
    String keyword;
    String userQuery = "bitcoin";
    Intent intent;
    String req = "https://newsapi.org/v2/everything?q=" + userQuery
            + "&from=2020-02-14&sortBy=publishedAt&apiKey=7706477163614421b4c5d5b6b9dcf354";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        input = findViewById(R.id.search);
        title = findViewById(R.id.title);

        button.setOnClickListener((v) -> {
            intent = new Intent(this, ListViewActivity.class);
            String text = input.getText().toString();
            intent.putExtra("keyword", text);

            userQuery = text;
            req = "https://newsapi.org/v2/everything?q=" + userQuery
                    + "&from=2020-02-14&sortBy=publishedAt&apiKey=7706477163614421b4c5d5b6b9dcf354";

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            ArticleArray.loadNewArticles(userQuery, requestQueue);

//
//            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, req, new JSONObject(), (res) -> {
//                System.out.println(res);
//                JSONArray articles_JSON = new JSONArray();
//                try {
//                    articles_JSON = res.getJSONArray("articles");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(articles); // gets all articles.
//                try {
//
//                    for (int i = 0; i < 10; i++) {
//                        String source = articles_JSON.getJSONObject(i).getJSONObject("source").getString("name"); // gets sources
//                        String author = articles_JSON.getJSONObject(i).getString("author"); // gets authors
//                        String title = articles_JSON.getJSONObject(i).getString("title"); // gets title
//                        String description = articles_JSON.getJSONObject(i).getString("description");
//                        String url = articles_JSON.getJSONObject(i).getString("url");
//                        String urlToImage = articles_JSON.getJSONObject(i).getString("urlToImage");
//                        String publishedAt = articles_JSON.getJSONObject(i).getString("publishedAt");
//                        String content = articles_JSON.getJSONObject(i).getString("content");
//                        Articles art = new Articles(source, author, title, description, url, urlToImage, publishedAt,
//                                content);
//                        articles.add(art);
//                    }
//
//                    System.out.println(articles.size());
//                    for (int i = 0; i < articles.size(); i++) {
////                        System.out.println(articles.get(i).getSource());
////                        System.out.println(articles.get(i).getAuthor());
////                        System.out.println(articles.get(i).getContent());
////                        System.out.println(articles.get(i).getDescription());
////                        System.out.println(articles.get(i).getPublishedAt());
////                        System.out.println(articles.get(i).getUrl());
////                        System.out.println(articles.get(i).getUrlToImage());
////                        System.out.println(i);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                requestQueue.stop();
//            }, (error) -> {
//               // textView.setText("Something went wrong...");
//                error.printStackTrace();
//                requestQueue.stop();
//            });
//            requestQueue.add(request);
            startActivity(intent);
        });
    }
}
