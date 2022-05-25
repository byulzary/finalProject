package com.example.finalproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastRecyclerAdapter extends RecyclerView.Adapter<PastRecyclerAdapter.MyViewHolder> {

    private ArrayList<PastLists> pastLists;

    public PastRecyclerAdapter(ArrayList<PastLists> pastLists) {
        this.pastLists = pastLists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView amountTxt;
        private Button viewListBtn;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.plTextViewName);
            amountTxt = view.findViewById(R.id.plId);
            itemView.findViewById(R.id.buttonViewPastList).setOnClickListener(V -> {
                watchList();
            });
        }
    }

    private void watchList() {
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
        int amount = pastLists.get(position).getAmount();
        String sAmount = Integer.toString(amount);
        holder.nameTxt.setText(name);
        holder.amountTxt.setText(sAmount);
    }

    @Override
    public int getItemCount() {
        return pastLists.size();
    }
}
