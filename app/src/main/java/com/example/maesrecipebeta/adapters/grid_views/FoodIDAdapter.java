package com.example.maesrecipebeta.adapters.grid_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.maesrecipebeta.R;

import java.util.ArrayList;

public class FoodIDAdapter extends ArrayAdapter<String> {

    private ArrayList<String> data;
    private Context mContext;

    public FoodIDAdapter(@NonNull Context context, ArrayList<String> items){
        super(context,0,items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_post_ml_results,parent,false);
        }
        String item  = getItem(position);
        TextView itemName = listItemView.findViewById(R.id.grid_item_post_ml_text);
        itemName.setText(item);
        return listItemView;
    }
}
