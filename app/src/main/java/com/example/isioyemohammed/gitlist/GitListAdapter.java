package com.example.isioyemohammed.gitlist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class GitListAdapter extends RecyclerView.Adapter<GitListAdapter.ViewHolder> {
    /**
     * GithubUsers - userLists.
     */
    private List<GithubUsers> userLists;
    /**
     * Context - context.
     */
    private Context context;

    /**
     * A GitListAdapter constructor.
     * @param userLists - UserList
     * @param context - context
     */
    public GitListAdapter(List<GithubUsers> userLists, Context context) {
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
        final String username = userLists.get(position).getLogin();
        final String image = userLists.get(position).getAvatarUrl();
        final String gitUrl = userLists.get(position).getUrl();

        holder.setValues(this.userLists.get(position));

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);
                detailIntent.putExtra("USERNAME", username);
                detailIntent.putExtra("IMAGE", image);
                detailIntent.putExtra("GITURL", gitUrl);
                v.getContext().startActivity(detailIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.userLists.size();
    }

    /**
     * ViewHolder extends the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * TextView - textViewUsername.
         */
        public TextView textViewUsername;
        /**
         * ImageView - imageView.
         */
        public ImageView imageView;
        /**
         * TextView - textViewCompany.
         */
        public TextView textViewCompany;
        /**
         * TextView - textViewGitUrl.
         */
        public TextView textViewGitUrl;

        /**
         *
         * @param itemView - ItemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            imageView = itemView.findViewById(R.id.imageView);
            textViewCompany = itemView.findViewById(R.id.textViewCompany);
            textViewGitUrl = itemView.findViewById(R.id.gitUrl);
        }

        /**
         *
         * @param userList - User list
         */
        public void setValues(@NonNull GithubUsers userList) {
            this.textViewUsername.setText(userList.getLogin());
            this.textViewCompany.setText(userList.getUrl());
        }
    }
}
