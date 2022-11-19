package com.example.forfoodiesbyfoodies;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView restaurantImage, imageViewStar1, imageViewStar2, imageViewStar3, imageViewStar4, imageStarHalf, btnReadMore;
    TextView restaurantName, restaurantType;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
    }

    public interface RecycleViewOnClickListener {
        void onPositionClicked(int position);
    }

}
