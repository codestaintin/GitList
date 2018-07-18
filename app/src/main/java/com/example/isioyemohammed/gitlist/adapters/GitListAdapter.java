package com.example.isioyemohammed.gitlist.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isioyemohammed.gitlist.R;
import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class GitListAdapter extends RecyclerView.Adapter<GitListAdapter.ViewHolder> {
    /**
     * GithubUsers - userLists.
     */
    private ArrayList<GithubUsers> userLists;

    /**
     * A GitListAdapter constructor.
     * @param userLists - UserList
     */
    public GitListAdapter(ArrayList<GithubUsers> userLists) {
        this.userLists = userLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String username = userLists.get(position).getLogin();
        final String image = userLists.get(position).getAvatarUrl();
        final String gitUrl = userLists.get(position).getUrl();

        holder.setValues(this.userLists.get(position));

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.image_placeholder)
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * TextView - textViewUsername.
         */
        public TextView textViewUsername;
        /**
         * ImageView - imageView.
         */
        public ImageView imageView;
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
            textViewGitUrl = itemView.findViewById(R.id.gitUrl);
        }

        /**
         *
         * @param userList - User list
         */
        public void setValues(@NonNull GithubUsers userList) {
            this.textViewUsername.setText(userList.getLogin());
        }
    }
}
