package com.maria.gallery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.maria.gallery.R;
import com.maria.gallery.mvp.model.data.File;
import com.maria.gallery.mvp.model.data.ImagesRow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImagesRowAdapter extends RecyclerView.Adapter<ImagesRowAdapter.ViewHolder> {

    private List<ImagesRow> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    private int screenWidth;

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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void configWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public interface OnItemClickListener {
        void onItemClick(String fileDownloadLink);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgLeft, imgRight;

        ViewHolder(View itemView) {
            super(itemView);

            imgLeft = itemView.findViewById(R.id.leftImg);
            imgRight = itemView.findViewById(R.id.rightImg);

            setImgWidth(imgLeft);
            setImgWidth(imgRight);

            imgLeft.setOnClickListener(this);
            imgRight.setOnClickListener(this);
        }

        private void setImgWidth(ImageView img) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
            params.width = screenWidth / 2;
            img.requestLayout();
        }

        void bindData(final ImagesRow imagesRow) {
            loadImage(imagesRow.getLeftPic(), imgLeft);
            loadImage(imagesRow.getRightPic(), imgRight);
        }

        private void loadImage(File image, ImageView img) {
            Picasso.get()
                    .load(image.getFileDownloadLink())
                    .resize(150, 150)
                    .centerCrop()
                    .into(img);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ImagesRow row = items.get(position);
                switch (v.getId()) {
                    case R.id.leftImg:
                        itemClick(row.getLeftPic());
                        break;
                    case R.id.rightImg:
                        itemClick(row.getRightPic());
                        break;
                }
            }
        }

        private void itemClick(File image) {
            onItemClickListener.onItemClick(image.getFileDownloadLink());
        }
    }
}
