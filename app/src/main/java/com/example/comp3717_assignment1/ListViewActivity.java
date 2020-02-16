package com.example.comp3717_assignment1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        listView = findViewById(R.id.list);
        loadNewArticles(keyword, requestQueue, this);
    }

    public static void printAllArticles(Context cv) {
        for (int i = 0; i < ArticleArray.articles.size(); i++) {
            arr.add(ArticleArray.articles.get(i).title());
        }
        adapter = new ArrayAdapter<>(cv, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailActivityIntent = new Intent(cv, DetailsActivity.class);
                detailActivityIntent.putExtra("index", position);
                cv.startActivity(detailActivityIntent);
            }
        });
    }

    public static void loadNewArticles(String userQuery, RequestQueue requestQueue, Context cv) {

        String url_req = "https://newsapi.org/v2/everything?q=" + userQuery
                + "&from=2020-02-14&sortBy=publishedAt&apiKey=" + API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_req, new JSONObject(), (res) -> {
            JSONArray jsonArr = new JSONArray();
            try {
                jsonArr = res.getJSONArray("articles");
                printAllArticles(cv);
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
