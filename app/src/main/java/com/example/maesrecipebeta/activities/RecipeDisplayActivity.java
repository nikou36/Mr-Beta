package com.example.maesrecipebeta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.models.SpoonacularRecipe;

public class RecipeDisplayActivity extends AppCompatActivity {
    private SpoonacularRecipe mRecipe;
    private ImageView imageView;
    private TextView mSummary;
    private TextView serveTime;
    private TextView serveSize;
    private TextView recipeLink;
    private TextView recipeTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        this.imageView = findViewById(R.id.recipe_display_activity_image_view);
        this.mSummary = findViewById(R.id.recipe_display_summary);
        this.serveTime = findViewById(R.id.recipe_display_cooking_time_text);
        this.serveSize = findViewById(R.id.recipe_display_serve_text);
        this.recipeLink = findViewById(R.id.recipe_display_link);
        this.recipeTitle = findViewById(R.id.recipe_display_title_text_view);
        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            this.mRecipe = extras.getParcelable("recipe");
            Glide.with(this).load(this.mRecipe.getImageUrl()).into(this.imageView);
            this.mSummary.setText(this.mRecipe.getSummary());
            this.serveTime.setText(this.mRecipe.getReadyTime());
            this.serveSize.setText(this.mRecipe.getServingSize());
            this.recipeTitle.setText(this.mRecipe.getTitle());
            this.recipeLink.setText(this.mRecipe.getUrl());
            this.recipeLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = mRecipe.getUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);
                }
            });
        }else{
            //TODO: Handle null case
        }

    }
}