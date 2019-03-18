package com.example.diego.staticcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class Search extends MainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //define the initial select from the bottom bar
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        loadFragment(new SearchFragment());

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Log.e("page is:", "profile");
                fragment = new ProfileFragment();
                break;
            case R.id.nav_activity:
                Log.e("page is:", "activity");
                fragment = new ActivityFragment();

                break;
            case R.id.nav_search:
                Log.e("page is:", "search");
                fragment = new SearchFragment();

                break;
            case R.id.nav_messages:
                Log.e("page is:", "messages");
                fragment = new MessagesFragment();

                break;
            case R.id.nav_menu:
                Log.e("page is:", "menu");

//open the drawer here



                break;
            default:
                break;
        }

        return loadFragment(fragment);
    }
}

