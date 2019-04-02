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
import android.widget.EditText;


import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private SearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    EditText searchInput;


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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<SearchItem> searchList = new ArrayList<>();
        searchList.add(new SearchItem(R.drawable.ic_account, "Maggie Vu", "Developer", "Highly motivated and passionate about Frontend development"));
        searchList.add(new SearchItem(R.drawable.ic_account_box, "Diego Rodrigues", "Developer", "Bachelor in Graphic Design and experienced in responsive web design. Skillful, hard-working, Post-degree at Langara."));
        searchList.add(new SearchItem(R.drawable.ic_android, "Noppawit Hansompob", "Developer", "Computer Science graduate. Experienced in Salesforce. Hardworking and passionate about what he does. In pathway to become full-stack web developer"));
        searchList.add(new SearchItem(R.drawable.ic_account, "Andra Iskandar", "Developer", "Bachelor’s in Information Systems. Solid background in QA testing and unit testing."));
        searchList.add(new SearchItem(R.drawable.ic_menu_camera, "Julia Stanovsky", "Designer", "With a bachelor’s degree of fine arts and animation, Julia enjoys creating beautiful and practical design solutions in her projects. She carries her sketch book and color pencils all the time so that she’s always ready to create great UX and UI design."));
        searchList.add(new SearchItem(R.drawable.ic_android, "Emily Kepler", "Graphic Designer", "Graphic Designer with 2+ years of experience in the management of the complete design process, from conceptualization to delivery."));
        searchList.add(new SearchItem(R.drawable.ic_menu_manage, "John Smith", "Web Designer", "Experienced UX designer. Mobile and website development including responsiveness and compatibility on modern devices."));
        searchList.add(new SearchItem(R.drawable.ic_account, "Tommy Lu", "Database Developer", "Diligent and productive database developer with a high level of work integrity. Experience developing server-side database management system apps on multiple platforms."));

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new SearchAdapter(searchList, getActivity().getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }
}
