package com.example.maesrecipebeta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.adapters.grid_views.FoodIDAdapter;
import com.example.maesrecipebeta.controllers.VolleyController;
import com.example.maesrecipebeta.models.SpoonacularRecipe;
import com.github.channguyen.adv.AnimatedDotsView;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;

public class PostMLScanActivity extends AppCompatActivity {

    private GridView foodsGrid;
    private Button goBackButton;
    private ArrayList<String> dishNames;
    private AnimatedDotsView dots;
    private ConstraintLayout parentLayout;
    private ImageView foodImage;
    private ArrayList<SpoonacularRecipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_m_l_scan);
        this.dots = (AnimatedDotsView) findViewById(R.id.post_ml_dots);
        this.parentLayout = findViewById(R.id.post_ml_parent_layout);
        this.foodsGrid = findViewById(R.id.post_ml_results_grid);
        this.foodImage = findViewById(R.id.post_ml_image_view);
        this.recipesList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        this.dishNames = extras.getStringArrayList("List");
        byte[] bytes = extras.getByteArray("bitmap");
        this.foodImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
        FoodIDAdapter adapter = new FoodIDAdapter(getApplicationContext(),dishNames);
        this.foodsGrid.setAdapter(adapter);
        this.foodsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = dishNames.get(position);
                Log.i("TEST GRID VIEW",item);
                dots.startAnimation();
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parentLayout);
                constraintSet.connect(R.id.post_ml_image_view,ConstraintSet.TOP,R.id.post_ml_loading_text,ConstraintSet.BOTTOM);
                constraintSet.applyTo(parentLayout);
                spoonacularCall(item);
                /*
                Intent i = new Intent(PostMLScanActivity.this,DisplaySpoonacularRecipesActivity.class);
                startActivity(i);*/
            }
        });

        this.goBackButton = findViewById(R.id.post_ml_go_back_button);
        this.goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostMLScanActivity.this,PreUploadActivity.class);
                startActivity(i);
            }
        });
    }

    private void spoonacularCall(String query){
        RequestQueue mQueue = VolleyController.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = getString(R.string.recipesAPI) + query;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int index = 0; index < response.length();index++){
                        JSONObject item = response.getJSONObject(index);
                        String summary = html2text(item.getString("summary"));
                        SpoonacularRecipe tempRecipe = new SpoonacularRecipe(item.getString("url"),item.getString("image"), item.getString("title"),""+item.getInt("readyTime"),
                                item.getString("foodType"),""+item.getInt("servings"),summary);
                        recipesList.add(tempRecipe);
                    }
                    mQueue.stop();
                    Intent i = new Intent(PostMLScanActivity.this,DisplaySpoonacularRecipesActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("data", recipesList);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                } catch (JSONException e) {
                    Log.i("JSON parsing Error","JSON parsing Error");
                    onError();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError();
            }
        });
        mQueue.start();
        mQueue.add(request);
    }

    private void onError(){
        dots.stopAnimation();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(parentLayout);
        constraintSet.connect(R.id.post_ml_image_view,ConstraintSet.TOP,R.id.post_ml_parent_layout,ConstraintSet.TOP);
        constraintSet.applyTo(parentLayout);
        Toast.makeText(getApplicationContext(),"Error Retriving Recipes. Please Try Again",Toast.LENGTH_SHORT);
    }


    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}