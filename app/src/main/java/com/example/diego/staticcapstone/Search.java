package com.example.diego.staticcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

public class Search extends MainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Search";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //define the initial select from the bottom bar
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        setUpDrawer();

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
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        LinearLayout nav_dwr = findViewById(R.id.nav_dwr);

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Log.e("page is:", "profile");


                //TODO: get the photo from the login modal and send to the profile fragment.
                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String userPhoto = intent.getStringExtra("userPhoto");
                Fragment fragment1 = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putString("userPhoto", userPhoto);
                fragment1.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment1).commit();
                item.setChecked(true);
//                fragment = new ProfileFragment();
                break;
            case R.id.nav_activity:
                Log.e("page is:", "activity");

                //commented out for the final presentation, not in scope
                //fragment = new ActivityFragment();

                break;
            case R.id.nav_search:
                Log.e("page is:", "search");
                fragment = new SearchFragment();


                break;
            case R.id.nav_messages:
                Log.e("page is:", "messages");
                //commented out for the final presentation, not in scope
                //fragment = new MessagesFragment();

                break;
            case R.id.nav_menu:
                Log.e("page is:", "menu");


                //open the drawer here

                if (drawerLayout.isDrawerVisible(Gravity.END)) {
                    item.setChecked(false);
                    drawerLayout.closeDrawer(GravityCompat.END);

                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                    item.setChecked(true);
                }

                break;
            default:
                break;
        }

        return loadFragment(fragment);
    }


    private void setUpDrawer() {
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

}

