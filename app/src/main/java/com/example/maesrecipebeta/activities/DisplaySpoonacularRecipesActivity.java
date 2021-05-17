package com.example.maesrecipebeta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.adapters.view_pagers.SpoonacularRecipesViewPagerAdapter;
import com.example.maesrecipebeta.models.SpoonacularRecipe;

import java.util.ArrayList;

public class DisplaySpoonacularRecipesActivity extends AppCompatActivity {
    private ArrayList<SpoonacularRecipe> recipes;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_spoonacular_recipes);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            this.recipes =  extras.getParcelableArrayList("data");
        }else{
            this.recipes = new ArrayList<>();
        }
        Log.i("SIZE TEST", this.recipes.size()+"");
        if(this.recipes.size() > 0 ){
            this.viewPager = findViewById(R.id.spoonacular_activity_view_pager);
            viewPager.setAdapter(new SpoonacularRecipesViewPagerAdapter(getApplicationContext(),recipes));
            this.viewPager.setCurrentItem(0);
            this.viewPager.setClipToPadding(false);
            this.viewPager.setPadding(100, 0, 100, 0);
            this.viewPager.setPageMargin(40);

        }

    }
}