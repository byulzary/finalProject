package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastRecyclerAdapter extends RecyclerView.Adapter<PastRecyclerAdapter.MyViewHolder> {

    private final ItemListClickListener itemClickListener;
    private ArrayList<PastLists> pastLists;

    public PastRecyclerAdapter(ArrayList<PastLists> pastLists, ItemListClickListener itemListClickListener) {
        this.pastLists = pastLists;

        this.itemClickListener = itemListClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView idTxt;
        private Button viewListBtn;


        public MyViewHolder(View view) {
            super(view);
            nameTxt = view.findViewById(R.id.plTextViewName);
            idTxt = view.findViewById(R.id.plId);
            viewListBtn = view.findViewById(R.id.buttonViewPastList);
        }
    }

    @NonNull
    @Override
    public PastRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_list_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PastRecyclerAdapter.MyViewHolder holder, int position) {
        String name = pastLists.get(position).getName();
        int id = pastLists.get(position).getId();
        String sid = Integer.toString(id);
        holder.nameTxt.setText(name);
        holder.idTxt.setText(sid);
        holder.viewListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onViewItemListClick(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pastLists.size();
    }
}
