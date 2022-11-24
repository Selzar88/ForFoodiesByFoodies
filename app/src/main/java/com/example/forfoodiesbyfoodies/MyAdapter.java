package com.example.forfoodiesbyfoodies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.example.forfoodiesbyfoodies.RV.RecycleViewInterface;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final RecycleViewInterface recycleViewInterface;

    Context context;
    ArrayList<FoodPlace> list;
    private Uri imgUri;

    public MyAdapter(Context context, ArrayList<FoodPlace> list,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.list = list;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.place_single,parent,false);
        return new MyViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

           FoodPlace foodPlace = list.get(position);
           holder.name.setText(foodPlace.getName());
           holder.location.setText(foodPlace.getLocation());
           holder.description.setText(foodPlace.getDescription());
           holder.rate.setText(foodPlace.getRate());
           holder.vegan.setText(foodPlace.getVegan());
           /*imgUri = Uri.parse(link);
            Picasso.get().load(imgUri).into(holder.imageView);*/
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView location;
        TextView description;
        TextView rate;
        TextView vegan;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface){
            super(itemView);
            name =itemView.findViewById(R.id.placeName);
            location=itemView.findViewById(R.id.placeLocation);
            description = itemView.findViewById(R.id.placeDescriprion);
            rate= itemView.findViewById(R.id.placeRate);
            vegan =itemView.findViewById(R.id.placeVegan);
            imageView = itemView.findViewById(R.id.imageRestaurantImage);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recycleViewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            recycleViewInterface.onItemClick(position);
                        }

                    }
                }
            });
        }



    }
}
