package com.example.diego.staticcapstone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

public class Search extends MainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


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
                if (drawerLayout.isDrawerOpen(Gravity.END)) {
                    item.setChecked(false);
                    drawerLayout.closeDrawers();

                } else {
                    drawerLayout.openDrawer(Gravity.END);
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
        Log.e("blabla","tomar no cu ");
    }

}

