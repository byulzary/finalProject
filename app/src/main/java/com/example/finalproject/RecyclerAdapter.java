package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Product> productList;
    public static ArrayList<UserProduct> userList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTxt, priceTxt, descTxt, idTxt;
        private Button plusBtn, minusBtn;
        private int amount, id;

        public MyViewHolder(View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textViewName);
            priceTxt = view.findViewById(R.id.textViewPrice);
            descTxt = view.findViewById(R.id.textViewDescription);
            plusBtn = view.findViewById(R.id.buttonPlus);
            minusBtn = view.findViewById(R.id.buttonMinus);
            idTxt = view.findViewById(R.id.textViewIdUser);


            itemView.findViewById(R.id.buttonPlus).setOnClickListener(v -> {
                String name = nameTxt.getText().toString();
                String description = descTxt.getText().toString();
                float price = Float.parseFloat(priceTxt.getText().toString());


                id = Integer.parseInt(idTxt.getText().toString());
                System.out.println(id);
                addToUserList(name, description, id, price);
            });
        }
    }

    private void addToUserList(String name, String description, int id, float price) {
        int amount;
        boolean found = false;
        UserProduct up = new UserProduct(name, description, id, price, 1);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == up.getId()) {
                amount = userList.get(i).getAmount();
                amount++;
                userList.get(i).setAmount(amount);
                found = true;
            }
        }
        if (found == false) {
            up.setAmount(1);
            userList.add(up);

        }
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getId() + "|" + userList.get(i).getProductName() + "|" + userList.get(i).getPrice() + "|" + userList.get(i).getAmount());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String name = productList.get(position).getProductName();
        String description = productList.get(position).getDescription();
        float price = productList.get(position).getPrice();
        String sPrice = Float.toString(price);
        int id = productList.get(position).getId();
        String sId = Integer.toString(id);
        holder.nameTxt.setText(name);
        holder.descTxt.setText(description);
        holder.priceTxt.setText(sPrice);
        System.out.println(id);
        holder.idTxt.setText(sId);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
