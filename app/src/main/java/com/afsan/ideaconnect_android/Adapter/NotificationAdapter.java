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
import com.afsan.ideaconnect_android.Model.NotificationModel;
import com.afsan.ideaconnect_android.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel model = list.get(position);
        holder.dp.setImageResource(model.getDp());
        holder.noti_name.setText(model.getNoti_name());
        holder.noti_content.setText(model.getNoti_content());
        holder.noti_time.setText(model.getNoti_time());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{


        ImageView dp;
        TextView noti_name, noti_content,noti_time;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            dp = itemView.findViewById(R.id.noti_profile);
            noti_name = itemView.findViewById(R.id.noti_name);
            noti_content = itemView.findViewById(R.id.noti_content);
            noti_time = itemView.findViewById(R.id.noti_time);


        }
    }
}
