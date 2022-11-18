package com.example.forfoodiesbyfoodies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<RestaurantClass> items;




    public MyAdapter(Context context, List<RestaurantClass> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.restaurantName.setText(items.get(position).getRestaurantName());
        holder.restaurantType.setText(items.get(position).getRestaurantType());
        holder.restaurantImage.setImageResource(items.get(position).getImage());
        holder.imageViewStar1.setImageResource(items.get(position).getStar1());
        holder.imageViewStar2.setImageResource(items.get(position).getStar2());
        holder.imageViewStar3.setImageResource(items.get(position).getStar3());
        holder.imageViewStar4.setImageResource(items.get(position).getStar4());
        holder.imageViewStar4.setImageResource(items.get(position).getStar5());
        holder.imageStarHalf.setImageResource(items.get(position).getStarHalf());
        holder.btnReadMore.setImageResource(items.get(position).getBtnReadMore());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
