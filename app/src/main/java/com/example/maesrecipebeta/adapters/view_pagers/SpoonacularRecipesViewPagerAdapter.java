package com.example.maesrecipebeta.adapters.view_pagers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.activities.RecipeDisplayActivity;
import com.example.maesrecipebeta.models.SpoonacularRecipe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class SpoonacularRecipesViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] images;
    private LayoutInflater mLayoutInflator;
    private ArrayList<SpoonacularRecipe> recipesData;

    public SpoonacularRecipesViewPagerAdapter(Context context,ArrayList<SpoonacularRecipe> data){
        this.mContext = context;
        //TODO:Delete and replace later
        this.images = new int[] {R.drawable.home_feed_test_image,R.drawable.home_feed_test_image,R.drawable.home_feed_test_image};
        this.mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recipesData = data;
    }

    @Override
    public int getCount(){return images.length;}

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object){
        return view == ((LinearLayout) object);
    }

    public Object instantiateItem(@NonNull ViewGroup container,final int position){
        // inflating the item.xml
        View itemView = mLayoutInflator.inflate(R.layout.spoonacular_view_pager_item, container, false);
        SpoonacularRecipe recipe = this.recipesData.get(position);
        TextView name = itemView.findViewById(R.id.spoonacular_recipe_title);
        TextView cookingTime = itemView.findViewById(R.id.spoonacular_cooking_time_text);
        TextView serveAmount = itemView.findViewById(R.id.spoonacular_serve_text);
        name.setText(recipe.getTitle());
        cookingTime.setText(recipe.getReadyTime() + " minutes");
        serveAmount.setText("Serves " + recipe.getServingSize());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TEST ON CLICK TAG", recipe.getTitle() );
                Intent i = new Intent(mContext, RecipeDisplayActivity.class);
                i.putExtra("recipe",recipe);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.spoonacular_view_pager_background);
        Glide.with(mContext).load(recipe.getImageUrl()).into(imageView);

        // setting the image in the imageView
        //imageView.setImageResource(images[position]);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);


        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }

}
