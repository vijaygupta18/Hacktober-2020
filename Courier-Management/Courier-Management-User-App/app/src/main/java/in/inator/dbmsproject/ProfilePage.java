package com.google.firebase.udacity.friendlychat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ProfilePage extends AppCompatActivity {

    private ImageButton backButton;
    private CircularImageView profileImg;
    private TextView name,team,points;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        backButton = findViewById(R.id.back_btn);
        profileImg = findViewById(R.id.profile_pic);    // profile pic circular imageView
        name = findViewById(R.id.tv_name);              // full name textview
        team = findViewById(R.id.tv_team);              // team name textview
        points = findViewById(R.id.tv_points);          // points textview

        //load profile pic
        Glide.with(this).load(R.drawable.user).into(profileImg);

        name.setText("Full Name"); //set name
        team.setText("Chennai Royale"); //set team name
        team.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.teeam,0,0,0);
        points.setText("1880"); // set team points

        //back button action
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
            }
        });

    }
}