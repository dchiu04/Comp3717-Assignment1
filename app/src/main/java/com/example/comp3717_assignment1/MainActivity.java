package com.example.comp3717_assignment1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String API = "7706477163614421b4c5d5b6b9dcf354";
    Button button;
    EditText input;
    TextView title;
    String userQuery;
    public static Date c = Calendar.getInstance().getTime();
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static final String formattedDate = df.format(c);

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
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            ArticleArray.loadArticles(userQuery, requestQueue, this);
        });
    }
}
