package com.example.comp3717_assignment1;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JSONparser {
    @SerializedName("dataItems")
    @Expose ArrayList<DataItems> data = new ArrayList<>();

    public ArrayList<DataItems> getDataItems() {
        return data;
    }

    public void setDataItems(ArrayList<DataItems> d) {
        this.data = d;
    }
}