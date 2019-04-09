package com.example.diego.staticcapstone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        EditText usermailedit = getView().findViewById(R.id.user_email_details_input);
        EditText usernameedit = getView().findViewById(R.id.user_name_details_input);
        Bundle bundle = this.getArguments();

        String usr = bundle.containsKey("userName") ? bundle.getString("userName") : "default";

        if (usr != null) {
            Uri myUri = Uri.parse(bundle.getString("userPhoto"));
            Glide.with(this).load(myUri).into(userphoto);

            username.setText(bundle.getString("userName"));
            usernameedit.setText(bundle.getString("userName"));
            usermailedit.setText(bundle.getString("userEmail"));

        } else {

        }


    }
}
