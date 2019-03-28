package com.example.diego.staticcapstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {
    private static final String TAG = "PROFILE";

    public ProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView username = getView().findViewById(R.id.user_name);
        ImageView userphoto = getView().findViewById(R.id.profile_pic);
        Bundle bundle = this.getArguments();

        String usr = bundle.containsKey("userName") ? bundle.getString("userName") : "default";
        if (usr != null) {
            Log.e(TAG, "username is not null");
            username.setText(bundle.getString("userName"));
            Glide.with(this).load(bundle.getString("userPhoto")).into(userphoto);
        } else {
            Log.e(TAG, "it's null");

        }


    }
}
