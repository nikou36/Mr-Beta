package com.example.maesrecipebeta.adapters.recycler_views;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.adapters.view_pagers.HomeFeedViewPagerAdapter;
import com.example.maesrecipebeta.models.Post;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

public class HomeFeedItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Post> values;
    private ViewPager mViewPager;
    private Context context;

    public HomeFeedItemAdapter(Context context,ArrayList<Post> items){
        this.values = items;
        this.context = context;
    }

    public class ImagePostViewHolder extends RecyclerView.ViewHolder{
        public TextView userName;
        public TextView dishName;
        public TextView summary;
        public TextView commentsCount;
        public TextView likesCount;
        public TextView time;
        public TextView location;
        public CircleImageView profilePic;
        public ViewPager dishPic;
        public ImageView likePic;
        public String recipeID;
        public View layout;
        public boolean liked;




        public ImagePostViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            userName = (TextView) itemView.findViewById(R.id.home_feed_profile_name);
            dishName = (TextView) itemView.findViewById(R.id.home_feed_recipe_title);
            summary = (TextView) itemView.findViewById(R.id.home_feed_description);
            time = (TextView) itemView.findViewById(R.id.home_feed_item_time);
            location = (TextView) itemView.findViewById(R.id.home_feed_location_text);
            commentsCount = (TextView) itemView.findViewById(R.id.home_feed_comments);
            likesCount = (TextView) itemView.findViewById(R.id.home_feed_likes_count);
            likePic = (ImageView) itemView.findViewById(R.id.home_feed_like);
            profilePic = (CircleImageView)itemView.findViewById(R.id.home_feed_profile_image);

            //Set up view pager
            dishPic = (ViewPager)itemView.findViewById(R.id.home_feed_viewpager);
            HomeFeedViewPagerAdapter adapter = new HomeFeedViewPagerAdapter(context);
            dishPic.setAdapter(adapter);
            dishPic.setCurrentItem(0);
            //Set up circle indicator
            CircleIndicator indicator = (CircleIndicator) itemView.findViewById(R.id.home_feed_indicator);
            indicator.setViewPager(dishPic);

            /*
            dishPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!recipeID.equals("none")){
                        //Intent i = new Intent(v.getContext(), CustomRecipeActivity.class);
                        Intent i = new Intent(v.getContext(), LoadRecipeActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(context,R.anim.bottom_up,R.anim.top_to_bottom);
                        i.putExtra("RecipeID",recipeID);
                        v.getContext().startActivity(i,options.toBundle());
                    }

                }
            });
            commentsCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(v.getContext(), LoadCommentsActivity.class);
                    Intent i = new Intent(v.getContext(), CommentsActivity.class);
                    i.putExtra("PostID",values.get(getAdapterPosition()).getPostID());
                    i.putExtra("CreatorID",values.get(getAdapterPosition()).getUserID());
                    v.getContext().startActivity(i);
                    //v.getContext().overridePendingTransition(R.anim.bottom_up, R.anim.nothing);
                }
            });
            likesCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), LikesActivity.class);
                    i.putExtra("PostID",values.get(getAdapterPosition()).getPostID());
                    v.getContext().startActivity(i);
                }
            });
            likePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Like Button Clicked","True");
                    Log.i("Post ID Clicked: ",values.get(getAdapterPosition()).getPostID());
                    if(running){
                        Toast.makeText(context,"Please wait a bit",Toast.LENGTH_SHORT).show();
                    }else{
                        running = true;
                        if(liked){
                            liked = false;
                            likePic.setBackgroundResource(R.drawable.unliked);
                            likesCount.setText(Integer.parseInt(likesCount.getText().toString()) - 1 + "");
                            likesDelete(values.get(getAdapterPosition()).getPostID(),values.get(getAdapterPosition()).getUserID());
                            //call delete api
                        }else{
                            liked = true;
                            likePic.setBackgroundResource(R.drawable.liked);
                            likesCount.setText(Integer.parseInt(likesCount.getText().toString()) + 1 + "");
                            //call create api
                            likesPost(values.get(getAdapterPosition()).getPostID(),values.get(getAdapterPosition()).getUserID());
                        }
                    }

                }
            });

             */
        }
    }

    public class RegularPostViewHolder extends RecyclerView.ViewHolder{
        public TextView userName;
        public TextView postText;
        public TextView likesCount;
        public TextView commentsCount;
        public TextView time;
        public TextView location;
        public ImageView profilePic;
        public ImageView likePic;
        public View layout;
        public boolean liked;

        public RegularPostViewHolder(View itemView){
            super(itemView);
            layout = itemView;
            userName = itemView.findViewById(R.id.home_feed_text_profile_name);
            postText = itemView.findViewById(R.id.home_feed_text_description);
            profilePic = itemView.findViewById(R.id.home_feed_text_profile_image);
            commentsCount = (TextView) itemView.findViewById(R.id.home_feed_text_comments);
            time = (TextView) itemView.findViewById(R.id.home_feed_text_item_time);
            location = (TextView) itemView.findViewById(R.id.home_feed_text_location_text);
            likesCount = (TextView) itemView.findViewById(R.id.home_feed_likes_count);
            likePic = (ImageView) itemView.findViewById(R.id.home_feed_text_like);
            /*
            likesCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), LikesActivity.class);
                    i.putExtra("PostID",values.get(getAdapterPosition()).getPostID());
                    v.getContext().startActivity(i);
                }
            });
            likePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Like Button Clicked","True");
                    if(running){
                        Toast.makeText(context,"Please wait a bit",Toast.LENGTH_SHORT).show();
                    }else{
                        running = true;
                        if(liked){
                            liked = false;
                            likePic.setBackgroundResource(R.drawable.unliked);
                            likesCount.setText(Integer.parseInt(likesCount.getText().toString()) - 1 + "");
                            //call delete api
                            likesDelete(values.get(getAdapterPosition()).getPostID(),values.get(getAdapterPosition()).getUserID());
                        }else{
                            liked = true;
                            likePic.setBackgroundResource(R.drawable.liked);
                            likesCount.setText(Integer.parseInt(likesCount.getText().toString()) + 1 + "");
                            ;
                            likesPost(values.get(getAdapterPosition()).getPostID(),values.get(getAdapterPosition()).getUserID());
                            //call create api
                        }
                    }

                }
            });
            commentsCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(v.getContext(), LoadCommentsActivity.class);
                    Intent i = new Intent(v.getContext(), CommentsActivity.class);
                    i.putExtra("PostID",values.get(getAdapterPosition()).getPostID());
                    i.putExtra("CreatorID",values.get(getAdapterPosition()).getUserID());
                    v.getContext().startActivity(i);
                    //v.getContext().overridePendingTransition(R.anim.bottom_up, R.anim.nothing);
                }
            });
            */

        }
    }

    /**************
     *
     * @param position: item position
     * @return 1 if it is just a text post, 0 for a post with images.
     */
    @Override
    public int getItemViewType(int position){
        if(values.get(position).getImageURL().equals("none")){
            return 1;
        }else{
            return 0;
        }
    }

    public void add(int position, Post item){
        values.add(position, item);
        notifyItemInserted(position);
    }


    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case 1:
                return new RegularPostViewHolder(inflater.inflate(R.layout.list_item_home_feed_text_only,parent,false));
            default:
                return new ImagePostViewHolder(inflater.inflate(R.layout.list_item_home_feed,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Post post = values.get(position);
        switch (holder.getItemViewType()){
            case 1:
                RegularPostViewHolder regHolder = (RegularPostViewHolder) holder;
                regHolder.postText.setText(post.getText());
                //TODO: CHANGE
                regHolder.profilePic.setImageResource(R.drawable.home_feed_test_image);
                regHolder.userName.setText(post.getUserID());
                regHolder.commentsCount.setText("See Comments("+post.getCommentsCount() + ")");
                regHolder.likesCount.setText(post.getLikesCount() + "");
                /* TODO: Implement
                regHolder.time.setText("");
                regHolder.location.setText("");
                */

                /* TODO: Dynamic likes
                if(userFavorites.contains(post.getPostID())){
                    regHolder.likePic.setBackgroundResource(R.drawable.liked);
                    regHolder.liked = true;
                }else{
                    regHolder.likePic.setBackgroundResource(R.drawable.unliked);
                    regHolder.liked = false;
                }

                 */
                break;
            default:
                ImagePostViewHolder imgHolder = (ImagePostViewHolder) holder;
                //TODO:CHANGE
                //Glide.with(context).load("https://maesrecipescd3538974fe54ebcad64b26756f6845d152139-dev.s3.amazonaws.com/public/2277d839-c5ff-475e-ba13-9e0c59e22f8a").into(imgHolder.dishPic);
                //imgHolder.dishPic.setBackgroundResource(R.drawable.taco2);
                //TODO:CHANGE
                imgHolder.profilePic.setImageResource(R.drawable.home_feed_test_image);
                imgHolder.commentsCount.setText("See Comments ("+post.getCommentsCount() + ")");
                imgHolder.likesCount.setText(post.getLikesCount() + "");
                imgHolder.summary.setText(post.getText());
                imgHolder.userName.setText(post.getUserID());
                imgHolder.dishName.setText(post.getTitle());
                /* TODO: Implement
                imgHolder.time.setText("");
                imgHolder.location.setText("");
                */
                if(!post.getRecipeID().equals("none")){
                    imgHolder.recipeID = post.getRecipeID();
                }
                Log.i("Bind Post ID", post.getPostID());
                /* TODO: Implement later
                if(userFavorites.contains(post.getPostID())){
                    imgHolder.likePic.setBackgroundResource(R.drawable.liked);
                    imgHolder.liked = true;
                }else{
                    imgHolder.likePic.setBackgroundResource(R.drawable.unliked);
                    imgHolder.liked = false;
                }*/
                break;
        }

    }

    @Override
    public int getItemCount() {
        return this.values.size();
    }


}
