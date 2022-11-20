package com.example.forfoodiesbyfoodies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<FoodPlace> list;

    public MyAdapter(Context context, ArrayList<FoodPlace> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.place_single,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           FoodPlace foodPlace = list.get(position);
           holder.name.setText(foodPlace.getName());
           holder.location.setText(foodPlace.getLocation());
           holder.descripsion.setText(foodPlace.getDescription());
           holder.rate.setText(foodPlace.getRate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, location, descripsion, rate;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name =itemView.findViewById(R.id.placeName);
            location=itemView.findViewById(R.id.placeLocation);
            descripsion= itemView.findViewById(R.id.placeDescriprion);
            rate= itemView.findViewById(R.id.placeRate);
        }


    }
}
