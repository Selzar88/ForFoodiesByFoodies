package com.example.forfoodiesbyfoodies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView restaurantName, restaurantReview, restaurantRate;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        restaurantName = itemView.findViewById(R.id.restaurantName);
        restaurantReview = itemView.findViewById(R.id.restaurantReview);
        restaurantRate = itemView.findViewById(R.id.restaurantRate);
    }
}
