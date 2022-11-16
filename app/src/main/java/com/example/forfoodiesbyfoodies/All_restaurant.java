package com.example.forfoodiesbyfoodies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class All_restaurant extends RecyclerView.Adapter<All_restaurant.MyViewHolder> {
    Context context;
    ArrayList<RestaurantClass> restaurantsView;

    public All_restaurant(ArrayList<RestaurantClass> restaurantsView){
        this.context=context;
        this.restaurantsView= restaurantsView;
    }

    @NonNull
    @Override
    public All_restaurant.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new All_restaurant.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull All_restaurant.MyViewHolder holder, int position) {

        holder.tvName.setText(restaurantsView.get(position).getRestaurantName());
        holder.tv3Letter.setText(restaurantsView.get(position).getRestaurantReview());
        holder.tv1Letter.setText(restaurantsView.get(position).getRestaurantRate());
        holder.imageView.setImageResource(restaurantsView.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return restaurantsView.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName, tv3Letter, tv1Letter;

        public MyViewHolder (@NonNull View itemView){
            super (itemView);

            imageView = itemView.findViewById(R.id.restaurantLogo);
            tvName = itemView.findViewById(R.id.restaurantName);
            tv3Letter = itemView.findViewById(R.id.review);
            tv1Letter = itemView.findViewById(R.id.rate);
        }
    }
}
