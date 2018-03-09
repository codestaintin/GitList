package com.example.isioyemohammed.gitlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class GitListAdapter extends RecyclerView.Adapter<GitListAdapter.ViewHolder> {

    private List<UserList> userLists;
    private Context context;

    public GitListAdapter(List<UserList> userLists, Context context) {
        this.userLists = userLists;
        this.context = context;
    }

    @NonNull
    @Override
    public GitListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GitListAdapter.ViewHolder holder, int position) {
        holder.setValues(this.userLists.get(position));
    }

    @Override
    public int getItemCount() {
        return this.userLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewUsername;
        public ImageView imageView;
        public TextView textViewCompany;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            imageView = itemView.findViewById(R.id.imageView);
            textViewCompany = itemView.findViewById(R.id.textViewCompany);
        }

        public void setValues(@NonNull UserList userList) {
            this.textViewUsername.setText(userList.getUsername());
            this.textViewCompany.setText(userList.getCompany());
        }
    }
}