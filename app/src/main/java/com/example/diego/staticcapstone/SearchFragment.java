package com.example.diego.staticcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

public class SearchFragment extends Fragment {
    //    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public SearchFragment() {
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();


        Intent intent = getActivity().getIntent();
        //to expand the search
        searchView.setIconified(false);


        String search = intent.getStringExtra("querySearch");
        searchView.setQuery(search, false);
        mAdapter.getFilter().filter(search);
        searchView.clearFocus();
//       Log.e("from home", "" + search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<SearchItem> searchList = new ArrayList<>();
        searchList.add(new SearchItem(R.drawable.ic_account, "Diego Rodrigues", "Designer", "Lorem ipsum dolor sit amet, photoshop consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et..."));
        searchList.add(new SearchItem(R.drawable.ic_account_box, "Bob Vance", "Owner", "Robert \"Bob\" Vance, is a fictional character played by Bobby Ray Shafer in the television series The Office. Illustrator"));
        searchList.add(new SearchItem(R.drawable.ic_android, "Person", "Something", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et..."));


        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchAdapter(searchList, getActivity().getApplicationContext());

//        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


//        return inflater.inflate(R.layout.fragment_search, null);\

        return rootView;
    }
}
