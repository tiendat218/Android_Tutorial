package com.example.api_weather.adapter;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api_weather.R;
import com.example.api_weather.model.Item;

import java.util.Date;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter {
    private List<Item> list;
    private Activity activity;

    public NewAdapter(List<Item> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    public void reloadData(List<Item> listData) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = activity.getLayoutInflater().inflate(R.layout.activity_main,parent,false);
        NewsHolder holder = new NewsHolder(itemView);

        return holder;
    }

    @Override
    public void  onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,int position){
        NewsHolder hd = (NewsHolder) holder;
        Item model = list.get(position);
        hd.tvLocation.setText(model.getLocation());
        hd.tvStatus.setText(model.getStatus());
        hd.tvTemperature.setText(model.getTemperature());
        Glide.with(activity).load(model.getImage()).into(hd.ivCover);

    }
    @Override
    public int getItemCount(){
        return list.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        TextView tvLocation,tvStatus,tvTemperature;
        ImageView ivCover;

        public NewsHolder(@NonNull View itemView){
            super(itemView);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}
