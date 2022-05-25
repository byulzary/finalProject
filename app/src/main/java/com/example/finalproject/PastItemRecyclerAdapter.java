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

public class PastItemRecyclerAdapter extends RecyclerView.Adapter<PastItemRecyclerAdapter.MyViewHolder> {

    private ArrayList<UserProduct> pastProductList;

    public PastItemRecyclerAdapter(ArrayList<UserProduct> pastLists) {
        this.pastProductList = pastLists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;
        private TextView descTxt;
        private TextView amountTxt;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textViewNamePast);
            descTxt = view.findViewById(R.id.textViewDescriptionPast);
            amountTxt = view.findViewById(R.id.textViewNumberPast);

        }
    }

    @NonNull
    @Override
    public PastItemRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_product_card,
                parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PastItemRecyclerAdapter.MyViewHolder holder, int position) {
        String name = pastProductList.get(position).getProductName();
        String desc = pastProductList.get(position).getDescription();
        int amount = pastProductList.get(position).getAmount();
        String sAmount = Integer.toString(amount);
        holder.nameTxt.setText(name);
        holder.descTxt.setText(desc);
        holder.amountTxt.setText(sAmount);
    }

    @Override
    public int getItemCount() {
        return pastProductList.size();
    }
}
