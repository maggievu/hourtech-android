package com.example.diego.staticcapstone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class Search_home extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_home);


        View backgroundImage = (View) findViewById(R.id.search_home);
        //hide the status bar from phone
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //background home alpha
//        Drawable background = backgroundImage.getBackground();
//        background.setAlpha(90);

    }

    public void afterLogin(View view) {
//        setContentView(R.layout.search);
        startActivity(new Intent(getApplicationContext(), Search.class));
    }




}