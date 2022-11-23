package com.example.forfoodiesbyfoodies.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.DetailsView;
import com.example.forfoodiesbyfoodies.Entities.Comment;
import com.example.forfoodiesbyfoodies.Entities.User;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.RV.RecycleViewInterface;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private final RecycleViewInterface recycleViewInterface;
    private Context context;
    private ArrayList<Comment> commentlist;

    public CommentAdapter(Context context, ArrayList<Comment> commentlist,RecycleViewInterface recycleViewInterface) {
        this.recycleViewInterface = recycleViewInterface;
        this.context = context;
        this.commentlist = commentlist;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_comment,parent,false);
        return new CommentViewHolder(v,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        Comment comment = commentlist.get(position);
        holder.name.setText(comment.getUserName());
        holder.content.setText(comment.getText());

    }

    @Override
    public int getItemCount() {
        return commentlist.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView name , content;
        public CommentViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface){
            super(itemView);
            name= itemView.findViewById(R.id.commentName);
            content = itemView.findViewById(R.id.commentContent);
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
