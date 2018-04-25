package com.maria.gallery.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.maria.gallery.R;
import com.maria.gallery.mvp.model.data.ImagesRow;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
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

    /*public void updateItems(List<ImagesRow> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }*/

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String fileDownloadLink);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img1, img2;

        ViewHolder(View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.leftImg);
            img2 = itemView.findViewById(R.id.rightImg);

            itemView.setOnClickListener(this);
            img1.setOnClickListener(this);
            img2.setOnClickListener(this);
        }

        void bindData(final ImagesRow imagesRow) {
            Picasso.get().load(imagesRow.getPic1().getFileDownloadLink()).into(img1);
            Picasso.get().load(imagesRow.getPic2().getFileDownloadLink()).into(img2);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                switch (v.getId()) {
                    case R.id.leftImg:
                        onItemClickListener.onItemClick(items.get(position).getPic1().getFileDownloadLink());
                        break;
                    case R.id.rightImg:
                        onItemClickListener.onItemClick(items.get(position).getPic2().getFileDownloadLink());
                        break;
                }
            }
        }
    }
}
