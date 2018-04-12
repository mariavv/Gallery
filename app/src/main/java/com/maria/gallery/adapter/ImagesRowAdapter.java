package com.maria.gallery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.maria.gallery.R;
import com.maria.gallery.mvp.model.ImagesRow;

import java.util.ArrayList;
import java.util.List;

public class ImagesRowAdapter extends RecyclerView.Adapter<ImagesRowAdapter.ViewHolder> {

    private List<ImagesRow> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ImagesRowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_images_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesRowAdapter.ViewHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ImagesRow entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(entity);

        notifyItemInserted(items.size() - 1);
    }

    public void updateItems(List<ImagesRow> items) {
        if (items == null) {
            return;
        }

        this.items = items;

        notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageButton img1;
        ImageButton img2;

        ViewHolder(View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.leftImg);
            img2 = itemView.findViewById(R.id.rightImg);

            itemView.setOnClickListener(this);
        }

        void bindData(final ImagesRow imagesRow) {
            img1.setImageResource(imagesRow.getPic1());
            img1.setImageResource(imagesRow.getPic2());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick();
            }
        }
    }
}
