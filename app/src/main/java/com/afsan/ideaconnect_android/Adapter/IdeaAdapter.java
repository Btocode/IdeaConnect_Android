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
import com.afsan.ideaconnect_android.R;

import java.util.ArrayList;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.viewHolder>{

    ArrayList<IdeaModel> list;
    Context context;

    public IdeaAdapter(ArrayList<IdeaModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_rv_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        IdeaModel model = list.get(position);
//        String suggestion = String.valueOf(model.getSuggestions().length);
        String suggestion = "55";
        String upvote = String.valueOf(model.getUpvotes().length);
        String downupvote = String.valueOf(model.getDownvotes().length);
        holder.profile.setImageResource(model.getProfile());
        holder.name.setText(model.getFirst_name() + model.getLast_name());
        holder.title.setText(model.getIdeaTitle());
        holder.tags.setText(model.getIdeaTags());
        holder.suggestion.setText(suggestion);
        holder.idea.setText(model.getIdeaDesc());
        holder.upvote.setText(upvote);
        holder.downvote.setText(downupvote);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{


        ImageView profile;
        TextView name, idea,suggestion, title,tags,upvote,downvote;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.username);
            idea = itemView.findViewById(R.id.ideatext);
            suggestion = itemView.findViewById(R.id.comment);
            tags = itemView.findViewById(R.id.tags);
            title = itemView.findViewById(R.id.ideatitle);
            upvote = itemView.findViewById(R.id.upvote);
            downvote = itemView.findViewById(R.id.downvote);

        }
    }
}
