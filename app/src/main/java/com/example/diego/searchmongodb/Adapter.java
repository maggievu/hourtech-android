package com.example.diego.searchmongodb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<ListItem> listItems;
    private List<ListItem> listItemsFull;
    private Context context;

    public Adapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

        //create a new list for the search with the full list
        listItemsFull = new ArrayList<>(listItems);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);

        viewHolder.usernameLabel.setText(listItem.getUsername());
        viewHolder.nameLabel.setText(listItem.getName());
        viewHolder.emailLabel.setText(listItem.getEmail());
        viewHolder.searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click on " + listItem.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView usernameLabel, nameLabel, emailLabel;
        public LinearLayout searchLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameLabel = (TextView) itemView.findViewById(R.id.usernameLabel);
            nameLabel = (TextView) itemView.findViewById(R.id.nameLabel);
            emailLabel = (TextView) itemView.findViewById(R.id.emailLabel);
            searchLayout = (LinearLayout) itemView.findViewById(R.id.searchLayout);
        }
    }



    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ListItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listItemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ListItem item : listItemsFull){
                    if (item.getUsername().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        };

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            listItems.clear();
            listItems.addAll((List) results.values);

            notifyDataSetChanged();
        }
    };
    @Override
    public Filter getFilter() {
        return filter;
    }
}
