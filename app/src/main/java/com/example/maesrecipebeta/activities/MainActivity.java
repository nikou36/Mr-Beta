package com.example.maesrecipebeta.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.maesrecipebeta.R;
import com.example.maesrecipebeta.activities.ui.login.CustomLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ImageButton mainActivityCameraButton;
    private ImageButton mainActivityMessagesButton;
    private ImageButton mainActivityPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        this.mainActivityCameraButton = findViewById(R.id.main_activity_post_image_button);
        this.mainActivityCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PreUploadActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.swipe_left,R.anim.swipe_right);
                startActivity(i,options.toBundle());
            }
        });

        //TODO: TEMPORARY TESTING, DELETE LATER
        this.mainActivityPostButton = findViewById(R.id.main_activity_post_text_button);
        this.mainActivityPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SimpleLoginActivity.class);
                startActivity(i);
            }
        });

        /*TODO: Finish setting up the other two button functions


         */

    }

}