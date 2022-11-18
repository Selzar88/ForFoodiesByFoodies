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
        View view = inflater.inflate(R.layout.recycle_item, parent, false);
        return new All_restaurant.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull All_restaurant.MyViewHolder holder, int position) {

        holder.restaurantName.setText(restaurantsView.get(position).getRestaurantName());
        holder.restaurantType.setText(restaurantsView.get(position).getRestaurantType());
        holder.restaurantImage.setImageResource(restaurantsView.get(position).getImage());
        holder.imageViewStar1.setImageResource(restaurantsView.get(position).getStar1());
        holder.imageViewStar2.setImageResource(restaurantsView.get(position).getStar2());
        holder.imageViewStar3.setImageResource(restaurantsView.get(position).getStar3());
        holder.imageViewStar4.setImageResource(restaurantsView.get(position).getStar4());
        holder.imageViewStar4.setImageResource(restaurantsView.get(position).getStar5());
        holder.imageStarHalf.setImageResource(restaurantsView.get(position).getStarHalf());
        holder.btnReadMore.setImageResource(restaurantsView.get(position).getStarHalf());

    }

    @Override
    public int getItemCount() {
        return restaurantsView.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView restaurantImage, imageViewStar1, imageViewStar2, imageViewStar3, imageViewStar4, imageStarHalf,btnReadMore;
        TextView restaurantName, restaurantType;

        public MyViewHolder (@NonNull View itemView){
            super (itemView);

            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantType = itemView.findViewById(R.id.restaurantType);
            imageViewStar1 = itemView.findViewById(R.id.imageViewStar1);
            imageViewStar2 = itemView.findViewById(R.id.imageViewStar2);
            imageViewStar3 = itemView.findViewById(R.id.imageViewStar3);
            imageViewStar4 = itemView.findViewById(R.id.imageViewStar4);
            imageStarHalf = itemView.findViewById(R.id.imageStarHalf);
            btnReadMore = itemView.findViewById(R.id.btnReadMore);

        }
    }
}
