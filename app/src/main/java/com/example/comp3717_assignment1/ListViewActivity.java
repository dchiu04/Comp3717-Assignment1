package com.example.comp3717_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class ListViewActivity extends AppCompatActivity {
    Intent intent;
    private static final String API_KEY = "7706477163614421b4c5d5b6b9dcf354";
    String keyword;
    String DATE = "yyy-MM-dd";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_view);
        }

        /**
         * Retrieves a json object from the passed in json object.
         * @param json The json object from which the returned json object will be retrieved.
         * @param key The key whose value is the json object to be returned.
         * @return A json object.
         * */
        public static JSONObject jsonObjectFromJSONForKey(JSONObject json, String key) {
            try {
                System.out.println(key);
                return json.getJSONObject(key);
            } catch (JSONException e) {
                return null;
            }
        }

        String k = "https://newsapi.org/v2/top-headlines?country=us&apiKey=7706477163614421b4c5d5b6b9dcf354/";
        JSONObject js = new JSONObject();
        // jsonObjectFromJSONForKey(js, k);

//        JSONObject responseJSON = JSONUtils.jsonObjectFromJSONForKey(json, "response");
//        if (responseJSON != null) {
//            JSONObject resultJSON = JSONUtils.jsonObjectFromJSONForKey(responseJSON, "result");
//        }
//
//        Url websiteURL = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=7706477163614421b4c5d5b6b9dcf354/");
//
//        JSONObject jsonObject = new JSONObject(key);
//
//        //JSONObject json = new JSONObject(IOUtils.toString(new URL("https://graph.facebook.com/me"), Charset.forName("UTF-8")));
//        Set<String> keys =jsonObject.keySet();
//        for(String ke:keys) {
//            System.out.println("Key :: "+ke +", Value :: "+jsonObject.get(ke));;
//        }

}
