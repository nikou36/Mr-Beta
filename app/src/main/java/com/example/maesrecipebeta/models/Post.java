package com.example.maesrecipebeta.models;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private boolean friends_private;
    private String recipeID;
    private String userID;
    private boolean public_private;
    private String SK;
    private boolean followers_private;
    private String timeStamp;
    private String text;
    private String postID;
    private String title;
    private String PK;
    private String imageURL;
    private int likesCount;
    private int commentsCount;

    public Post(){}

    public Post(boolean friends_private, String recipeID, String userID, boolean public_private, String SK, boolean followers_private, String timeStamp, String text, String postID, String title, String PK, String imageURL) {
        this.friends_private = friends_private;
        this.recipeID = recipeID;
        this.userID = userID;
        this.public_private = public_private;
        this.SK = SK;
        this.followers_private = followers_private;
        this.timeStamp = timeStamp;
        this.text = text;
        this.postID = postID;
        this.title = title;
        this.PK = PK;
        this.imageURL = imageURL;
    }

    @TargetApi(value = 29)
    public Post(Parcel in){
        this.friends_private = in.readBoolean();
        this.recipeID = in.readString();
        this.userID = in.readString();
        this.public_private = in.readBoolean();
        this.SK = in.readString();
        this.followers_private = in.readBoolean();
        this.timeStamp = in.readString();
        this.text = in.readString();
        this.postID = in.readString();
        this.PK = in.readString();
        this.imageURL = in.readString();
        this.title = in.readString();
        this.likesCount = in.readInt();
        this.commentsCount = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFriends_private() {
        return friends_private;
    }

    public void setFriends_private(boolean friends_private) {
        this.friends_private = friends_private;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isPublic_private() {
        return public_private;
    }

    public void setPublic_private(boolean public_private) {
        this.public_private = public_private;
    }

    public String getSK() {
        return SK;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    public boolean isFollowers_private() {
        return followers_private;
    }

    public void setFollowers_private(boolean followers_private) {
        this.followers_private = followers_private;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPK() {
        return PK;
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    @Override
    public int describeContents(){return 0;}



    @TargetApi(value = 29)
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeBoolean(friends_private);
        dest.writeString(recipeID);
        dest.writeString(userID);
        dest.writeBoolean(public_private);
        dest.writeString(SK);
        dest.writeBoolean(followers_private);
        dest.writeString(timeStamp);
        dest.writeString(text);
        dest.writeString(postID);
        dest.writeString(PK);
        dest.writeString(imageURL);
        dest.writeString(title);
        dest.writeInt(likesCount);
        dest.writeInt(commentsCount);
    }

    public static final Parcelable.Creator<Post> CREATOR
            = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

}