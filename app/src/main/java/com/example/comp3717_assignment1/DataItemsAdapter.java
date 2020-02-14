package com.example.comp3717_assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataItemsAdapter extends ArrayAdapter<DataItems> {
    Context _context;
    public DataItemsAdapter(Context context, ArrayList<DataItems> toons) {
        super(context, 0, toons);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        DataItems toon = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvFullName = convertView.findViewById(R.id.fullName);
        TextView tvOccupation = convertView.findViewById(R.id.occupation);
        TextView tvGender = convertView.findViewById(R.id.gender);

        // Populate the data into the template view using the data object
        tvFullName.setText(String.format("%s %s", toon.getFirstName(), toon.getLastName()));
        tvOccupation.setText(toon.getOccupation());
        tvGender.setText(toon.getGender());

        ImageView imgOnePhoto = convertView.findViewById(R.id.thumbImage);
        if (toon.getPictureUrl() != null) {
            new ImageDownloader(imgOnePhoto).execute(toon.getPictureUrl());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
