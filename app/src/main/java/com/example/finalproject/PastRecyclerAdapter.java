package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastRecyclerAdapter extends RecyclerView.Adapter<PastRecyclerAdapter.MyViewHolder> {

    private ArrayList<Product> pastProductList;

    public PastRecyclerAdapter(ArrayList<Product> productList) {
        this.pastProductList = productList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textViewNamePast);

        }
    }

    @NonNull
    @Override
    public PastRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_product_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PastRecyclerAdapter.MyViewHolder holder, int position) {
        String name = pastProductList.get(position).getProductName();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return pastProductList.size();
    }
}
