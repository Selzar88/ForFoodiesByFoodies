package com.example.forfoodiesbyfoodies;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView restaurantImage, imageViewStar1, imageViewStar2, imageViewStar3, imageViewStar4, imageStarHalf, btnReadMore;
    TextView restaurantName, restaurantType ;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

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
