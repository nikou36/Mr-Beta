package com.example.maesrecipebeta.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.adapters.recycler_views.HomeFeedItemAdapter;
import com.example.maesrecipebeta.models.Post;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView homeFeedView;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /* TODO: delete when done test only*/
        ArrayList<Post> test = new ArrayList<Post>();
        Post one = new Post(false,"testRecipe1","Emma Millstein",false,"SSSSS",false,"ttttttt","A great recipe for the whole family","FDFFDFDFD","Some good bread",
                "fdffdfdfd","google.com");
        Post two = new Post(false,"testRecipe2","Claire Rei",false,"SSSSS",false,"ttttttt","Looking for Bread Recipes!","FDFFDFDFD","Some good bread",
                "fdffdfdfd","none");
        Post three = new Post(false,"testRecipe3","Emma Millstein",false,"SSSSS",false,"ttttttt","A great recipe for the whole family","FDFFDFDFD","More bread",
                "fdffdfdfd","google.com");
        test.add(one);
        test.add(two);
        test.add(three);

        //**********************************//
        this.homeFeedView = (RecyclerView) root.findViewById(R.id.home_recycler_view);
        homeFeedView.setHasFixedSize(true);
        this.mAdapter = new HomeFeedItemAdapter(getContext(),test);
        this.layoutManager = new LinearLayoutManager(getContext());
        this.homeFeedView.setAdapter(this.mAdapter);
        this.homeFeedView.setLayoutManager(this.layoutManager);
        this.homeFeedView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        /*
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}