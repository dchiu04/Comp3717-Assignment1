package com.example.comp3717_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt_keyword;
    Button btn_search;

    Intent intent;
    String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   edt_keyword = findViewById(R.id.edt_keyword);
        edt_keyword.requestFocus();
       // btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent = new Intent(v.getContext(), TitleActivity.class);
                keyword = edt_keyword.getText().toString();
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });
    }
}
