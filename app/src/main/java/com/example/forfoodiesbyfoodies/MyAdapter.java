package com.example.forfoodiesbyfoodies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<FoodPlace> items;
    private ArrayList<RestaurantClass> restaurantsList;
    private RecyclerViewClickListener listener;





    public MyAdapter(Context context, ArrayList<FoodPlace> items) {
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
        holder.restaurantName.setText(items.get(position).getName());
        holder.restaurantType.setText(items.get(position).getDescribtion());
//        holder.restaurantImage.setImageResource(items.get(position).getImage());
//        holder.restaurantRate.setText(items.get(position).getRate());
//        holder.imageViewStar2.setImageResource(items.get(position).getStar2());
//        holder.imageViewStar3.setImageResource(items.get(position).getStar3());
//        holder.imageViewStar4.setImageResource(items.get(position).getStar4());
//        holder.imageViewStar4.setImageResource(items.get(position).getStar5());
//        holder.imageStarHalf.setImageResource(items.get(position).getStarHalf());
//        holder.btnReadMore.setImageResource(items.get(position).getBtnReadMore());

    }

    @Override
    public int getItemCount() {return items.size();}

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }









}
