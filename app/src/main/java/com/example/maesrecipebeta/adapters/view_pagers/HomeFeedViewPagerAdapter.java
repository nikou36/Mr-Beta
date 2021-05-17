package com.example.maesrecipebeta.adapters.view_pagers;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.maesrecipebeta.R;

import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

public class HomeFeedViewPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] images;
    private LayoutInflater mLayoutInflator;

    public HomeFeedViewPagerAdapter(Context context/*,int[] images*/){
        this.context = context;
        //TODO: For testing, Delete later
        this.images = new int[] {R.drawable.home_feed_test_image,R.drawable.home_feed_filled_heart,R.drawable.home_feed_empty_heart};
        // uncomment later this.images = images;
        this.mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return images.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object){
        return view == ((LinearLayout) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflator.inflate(R.layout.home_feed_view_pager_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.home_view_pager_image);

        // setting the image in the imageView
        imageView.setImageResource(images[position]);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
