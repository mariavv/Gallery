package com.maria.gallery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.maria.gallery.R;
import com.maria.gallery.mvp.ImagesRow;

import java.util.ArrayList;
import java.util.List;

public class ImagesRowAdapter extends RecyclerView.Adapter<ImagesRowAdapter.ViewHolder> {

    private List<ImagesRow> items;

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

        notifyDataSetChanged();
    }

    public void updateItems(List<ImagesRow> items) {
        if (items == null) {
            return;
        }

        this.items = items;

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton img1;
        ImageButton img2;

        public ViewHolder(View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.leftImg);
            img2 = itemView.findViewById(R.id.rightImg);
        }

        public void bindData(final ImagesRow imagesRow) {
            img1.setImageResource(imagesRow.getPic1());
            img1.setImageResource(imagesRow.getPic2());
        }
    }
}
