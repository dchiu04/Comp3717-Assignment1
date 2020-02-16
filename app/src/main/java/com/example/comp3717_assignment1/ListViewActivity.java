package com.example.comp3717_assignment1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        loadArticles(keyword, requestQueue, this);
    }

    public static void setAdapter(Context cv) {
        for (int i = 0; i < ArticleArray.articles.size(); i++) {
            arr.add(ArticleArray.articles.get(i).getTitle());
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

    public static void loadArticles(String userQuery, RequestQueue requestQueue, Context cv) {

        String URL = "https://newsapi.org/v2/everything?q=" + userQuery
                + "&from=" + MainActivity.formattedDate + "&sortBy=publishedAt&apiKey=" + MainActivity.API;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, new JSONObject(), (res) -> {
            JSONArray jsonArr = new JSONArray();
            try {
                jsonArr = res.getJSONArray("articles");
                setAdapter(cv);
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
