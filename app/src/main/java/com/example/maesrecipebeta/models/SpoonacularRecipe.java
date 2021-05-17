package com.example.maesrecipebeta.models;

/*
 Data Model for the Spoonacular call
 */

import android.os.Parcel;
import android.os.Parcelable;

public class SpoonacularRecipe implements Parcelable {
    private String url;
    private String imageUrl;
    private String title;
    private String readyTime;
    private String foodType;
    private String servingSize;
    private String summary;

    public SpoonacularRecipe(String url, String imageUrl, String title, String readyTime, String foodType, String servingSize, String summary) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.title = title;
        this.readyTime = readyTime;
        this.foodType = foodType;
        this.servingSize = servingSize;
        this.summary = summary;
    }

    public SpoonacularRecipe(Parcel in){
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
        this.readyTime = in.readString();
        this.foodType = in.readString();
        this.servingSize = in.readString();
        this.summary = in.readString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(String readyTime) {
        this.readyTime = readyTime;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(readyTime);
        dest.writeString(foodType);
        dest.writeString(servingSize);
        dest.writeString(summary);
    }

    public static final Parcelable.Creator<SpoonacularRecipe> CREATOR
            = new Parcelable.Creator<SpoonacularRecipe>() {
        public SpoonacularRecipe createFromParcel(Parcel in) {
            return new SpoonacularRecipe(in);
        }

        public SpoonacularRecipe[] newArray(int size) {
            return new SpoonacularRecipe[size];
        }
    };
}