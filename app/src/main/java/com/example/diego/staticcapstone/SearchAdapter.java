package com.example.diego.staticcapstone;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    private ArrayList<SearchItem> mExampleList;
    private ArrayList<SearchItem> mExampleListFull;

    public interface onItemClickListener {
        void onItemClick(int position);
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView1, mTextView2, mTextView3;

        public RelativeLayout relativeLayout;


        public SearchViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.tech_pic);
            mTextView1 = itemView.findViewById(R.id.tech_name);
            mTextView2 = itemView.findViewById(R.id.tech_role);
            mTextView3 = itemView.findViewById(R.id.tech_desc);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);


        }
    }

    private Context context;

    public SearchAdapter(ArrayList<SearchItem> searchList, Context context) {
        mExampleList = searchList;
        mExampleListFull = new ArrayList<>(searchList);
        this.context = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        SearchViewHolder evh = new SearchViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        final SearchItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getTech_pic());
        holder.mTextView1.setText(currentItem.getTech_name());
        holder.mTextView2.setText(currentItem.getTech_role());
        holder.mTextView3.setText(currentItem.getTech_desc());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Click on " + currentItem.getTech_name(), Toast.LENGTH_SHORT).show();


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                //load the fragment_Search_item here
                Fragment myFragment = new SearchItemFragment();

                Bundle bundle = new Bundle();
                bundle.putString("name", currentItem.getTech_name());
                bundle.putString("role", currentItem.getTech_role());
                bundle.putString("desc", currentItem.getTech_desc());
                bundle.putInt("pic", currentItem.getTech_pic());

                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();


            }


        });
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;

    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SearchItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mExampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SearchItem item : mExampleListFull) {
                    if (item.getTech_desc().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        ;

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
