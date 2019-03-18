package com.example.diego.staticcapstone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchItemFragment extends Fragment {

    public SearchItemFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_search_item, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        String nameString = getArguments().getString("name");
        String roleString = getArguments().getString("role");
        String descString = getArguments().getString("desc");
        Integer picString = getArguments().getInt("pic");
        if (view != null) {
            TextView title = view.findViewById(R.id.name_search_item);
            TextView role = view.findViewById(R.id.role_search_item);
            TextView desc = view.findViewById(R.id.desc_search_item);
            ImageView pic = view.findViewById(R.id.profile_search_item);
            title.setText(nameString);
            role.setText(roleString);
            desc.setText(descString);
            pic.setImageResource(picString);

        }
    }

}
