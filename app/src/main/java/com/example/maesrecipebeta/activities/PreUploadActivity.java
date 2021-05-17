package com.example.maesrecipebeta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.ml.LiteModelAiyVisionClassifierFoodV11;
import com.example.maesrecipebeta.models.TFLiteScore;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PreUploadActivity extends AppCompatActivity {
    private TextView takePhotoText;
    private TextView selectFromGalleryText;
    private Bitmap selectedBitmap;
    private ArrayList<String> dishes;
    private static final int NUM_RESULTS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_upload);
        this.takePhotoText = findViewById(R.id.preUpload_gallery_take_photo_text);
        this.selectFromGalleryText = findViewById(R.id.preUpload_select_gallery_text);
        this.dishes  = new ArrayList<>();
        this.takePhotoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any ac
            }
        });

        this.selectFromGalleryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
    }

    private void callBuiltInModel(Bitmap bitmap){
        try {
            LiteModelAiyVisionClassifierFoodV11 model = LiteModelAiyVisionClassifierFoodV11.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(bitmap);

            // Runs model inference and gets result.
            LiteModelAiyVisionClassifierFoodV11.Outputs outputs = model.process(image);

            List<Category> probability = outputs.getProbabilityAsCategoryList();
            float max = (float)0.0;
            int index = 0;

            //Queues up the top 5 scores
            PriorityQueue<TFLiteScore> scoreIndex = new PriorityQueue<>();

            for(int i = 0; i < probability.size();i++){
                Category curr = probability.get(i);
                float score = curr.getScore();
                int tempIndex = i;

                scoreIndex.add(new TFLiteScore(tempIndex,new Double(score)));

                if(max < score){
                    max = score;
                    index = tempIndex;
                }
            }
            ArrayList<TFLiteScore> topFive = new ArrayList<>();
            for(int i = 0; i < NUM_RESULTS;i++){
                topFive.add(scoreIndex.poll());
            }
            InputStream is = getResources().openRawResource(R.raw.aiy_food_label_map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            Log.i("SUCCESS","READ SUCESSFUL");
            String line ="";
            String dish="";
            int indexRead = 0;
            String[] labels = new String[2025];
            try{
                while(((line = br.readLine()) != null) /*&& indexRead <= index*/) {
                    if(indexRead == 0){
                        indexRead++;
                        continue;
                    }else{
                        String[] results = line.split(",");
                        labels[indexRead] =  results[1];
                        indexRead++;
                    }

                }
            }catch(IOException e){
                Log.i("ERROR",e.toString());
            }
            Log.i("INDEX",""+index);
            Log.i("SCORE",""+max);
            Log.i("TEST",labels[1]);
            // Releases model resources if no longer used.
            model.close();
            for(TFLiteScore score:topFive){
                System.out.println(score.getIndex());
                this.dishes.add(labels[score.getIndex() + 1]);
                System.out.println(labels[score.getIndex() + 1]);
            }
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    if(imageBitmap != null){
                        callBuiltInModel(imageBitmap);
                        Intent i = new Intent(PreUploadActivity.this,PostMLScanActivity.class);
                        i.putExtra("List",this.dishes);
                        byte[] byteArray = convertBitmapToByteArray(imageBitmap);
                        i.putExtra("bitmap",byteArray);
                        startActivity(i);
                        finish();
                    }

                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        if(bitmap != null){
                            callBuiltInModel(bitmap);
                            Intent i = new Intent(PreUploadActivity.this,PostMLScanActivity.class);
                            i.putExtra("List",this.dishes);
                            byte[] byteArray = convertBitmapToByteArray(bitmap);
                            i.putExtra("bitmap",byteArray);
                            startActivity(i);
                            finish();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}