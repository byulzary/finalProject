package com.example.finalproject;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CellViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public CellViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void setCell(Cell cell) {
        this.view.setBackgroundColor(cell.getColor());
    }
}
