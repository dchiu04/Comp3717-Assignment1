package com.example.comp3717_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "http://data.vncvr.ca/api/people/";
    //REAL LINK: "https://newsapi.org/v2/"

    private ArrayList<DataItems> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arr = new ArrayList<>();
        lv = findViewById(R.id.arr);
        new GetContacts().execute();

    }
    //   edt_keyword = findViewById(R.id.edt_keyword);
    //      edt_keyword.requestFocus();
    // btn_search = findViewById(R.id.btn_search);
//
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intent = new Intent(v.getContext(), TitleActivity.class);
//                keyword = edt_keyword.getText().toString();
//                intent.putExtra("keyword", keyword);
//                startActivity(intent);
//            }
//        });


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                // this step is needed to wrap the JSON array inside
                // a JSON object that looks like this { "toons": . . . . }
                jsonStr = "{\"toons\":" + jsonStr + "}";
                Gson gson = new Gson();
                JSONparser baseToon = gson.fromJson(jsonStr, JSONparser.class);
                arr = baseToon.getDataItems();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            DataItemsAdapter adapter = new DataItemsAdapter(MainActivity.this, arr);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }

}
