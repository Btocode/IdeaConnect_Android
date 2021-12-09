package com.afsan.ideaconnect_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afsan.ideaconnect_android.Model.IdeaModel;
import com.afsan.ideaconnect_android.Model.TrendingPostModel;
import com.afsan.ideaconnect_android.R;

import java.util.ArrayList;

public class TrendingPostAdapter extends RecyclerView.Adapter<TrendingPostAdapter.viewHolder>{

    ArrayList<TrendingPostModel> list;
    Context context;

    public TrendingPostAdapter(ArrayList<TrendingPostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.trending_sample_rv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TrendingPostModel model = list.get(position);
        holder.profile.setImageResource(model.getProfile());
        holder.name.setText(model.getName());
        holder.title.setText(model.getTitle());
        holder.tags.setText(model.getTags());
        holder.suggestion.setText(model.getSuggestion());
        holder.upvote.setText(model.getUpvote());
        holder.downvote.setText(model.getDownvote());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{


        ImageView profile;
        TextView name, suggestion, title,tags,upvote,downvote;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.username);
            suggestion = itemView.findViewById(R.id.comment);
            tags = itemView.findViewById(R.id.tags);
            title = itemView.findViewById(R.id.ideatitle);
            upvote = itemView.findViewById(R.id.upvote);
            downvote = itemView.findViewById(R.id.downvote);

        }
    }
}
