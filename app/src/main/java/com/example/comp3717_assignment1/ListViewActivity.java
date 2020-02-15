package com.example.comp3717_assignment1;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ListViewActivity extends AppCompatActivity {
    private static final String API_KEY = "7706477163614421b4c5d5b6b9dcf354";
    String keywords;
    String DATE = "yyy-MM-dd";
    ListView listView;
    String keyword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Intent intent = new Intent();
        RequestQueue requestQueue = Volley.newRequestQueue(ListViewActivity.this);
        keyword = intent.getStringExtra("keyword");
        listView = findViewById(R.id.list);
        loadNewArticles(keyword, requestQueue);
    }

    public static void printAllArticles() {
        //listView.
        for (int i = 0; i < ArticleArray.articles.size(); i++)
            System.out.println(ArticleArray.articles.get(i).title());
    }

    public static void loadNewArticles(String userQuery, RequestQueue requestQueue) {

        String req = "https://newsapi.org/v2/everything?q=" + userQuery
                + "&from=2020-01-14&sortBy=publishedAt&apiKey=" + API_KEY;

        ArticleArray.articles.clear();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, req, new JSONObject(), (res) -> {
            //System.out.println(res);
            JSONArray ourShit = new JSONArray();
            try {
                ourShit = res.getJSONArray("articles");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                for (int i = 0; i < 10; i++) {
                    String source = ourShit.getJSONObject(i).getJSONObject("source").getString("name"); // gets sources
                    String author = ourShit.getJSONObject(i).getString("author"); // gets authors
                    String title = ourShit.getJSONObject(i).getString("title"); // gets title
                    String description = ourShit.getJSONObject(i).getString("description");
                    String url = ourShit.getJSONObject(i).getString("url");
                    String urlToImage = ourShit.getJSONObject(i).getString("urlToImage");
                    String publishedAt = ourShit.getJSONObject(i).getString("publishedAt");
                    String content = ourShit.getJSONObject(i).getString("content");
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


