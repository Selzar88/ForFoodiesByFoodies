package com.example.forfoodiesbyfoodies.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.RecycleViewInterface;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private final RecycleViewInterface recycleViewInterface;
    private Context context;
    private ArrayList<User> userlist;

    public UserAdapter(Context context, ArrayList<User> list,RecycleViewInterface recycleViewInterface) {
        this.context = context;
        this.userlist = list;
        this.recycleViewInterface = recycleViewInterface;
    }


    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_user,parent,false);
        return new UserViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userlist.get(position);
        holder.firstname.setText(user.getFirstname());
        holder.surname.setText(user.getSurname());
        holder.email.setText(user.getEmail());
        holder.role.setText(user.getRole());
    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView firstname, surname, email, role, password;
        public UserViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface){
            super(itemView);
            firstname =itemView.findViewById(R.id.userProfileName);
            surname=itemView.findViewById(R.id.profileSurname);
            email = itemView.findViewById(R.id.userProfileEmail);
            role= itemView.findViewById(R.id.userProfileRole);
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
