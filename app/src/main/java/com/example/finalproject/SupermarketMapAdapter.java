package com.example.finalproject;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SupermarketMapAdapter extends RecyclerView.Adapter<CellViewHolder> {
    private final AsyncListDiffer<Cell> mDiffer = new AsyncListDiffer<>(this, new DiffUtil.ItemCallback<Cell>() {
        @Override
        public boolean areItemsTheSame(@NonNull Cell oldItem, @NonNull Cell newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Cell oldItem, @NonNull Cell newItem) {
            return areItemsTheSame(oldItem, newItem);
        }
    });

    private final int columns;

    public SupermarketMapAdapter(Matrix matrix, int columns) {
        this.columns = columns;
        setMatrix(matrix);
    }

    public void setMatrix(Matrix matrix) {
        mDiffer.submitList(matrix.asList());
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = new View(parent.getContext());
        v.setMinimumWidth(parent.getMeasuredWidth());
        v.setMinimumHeight(parent.getMeasuredHeight() / this.columns);
        return new CellViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {
        holder.setCell(mDiffer.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }
}
