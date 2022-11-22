package com.example.forfoodiesbyfoodies.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.FoodPlace;
import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.MyAdapter;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.RV.RecycleViewInterface;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private final RecycleViewInterface recycleViewInterface;
    private Context context;
    private ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.list = list;
        this.recycleViewInterface = recycleViewInterface;
    }


    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_user,parent,false);
        return new UserAdapter.UserViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = list.get(position);
        holder.name.setText(user.getFirstname());
        holder.surname.setText(user.getSurname());
        holder.email.setText(user.getEmail());
        holder.role.setText(user.getRole());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name, surname, email, role;
        public UserViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface){
            super(itemView);
            name =itemView.findViewById(R.id.userProfileName);
            surname=itemView.findViewById(R.id.userProfilSurname);
            email = itemView.findViewById(R.id.userProfileEmail);
            role= itemView.findViewById(R.id.userRole);

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
