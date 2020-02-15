package com.example.comp3717_assignment1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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
            String text = input.getText().toString();
            userQuery = text;
            req = "https://newsapi.org/v2/everything?q=" + userQuery
                    + "&from=2020-02-14&sortBy=publishedAt&apiKey=7706477163614421b4c5d5b6b9dcf354";

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            ArticleArray.loadNewArticles(userQuery, requestQueue, this);
        });
    }
}
