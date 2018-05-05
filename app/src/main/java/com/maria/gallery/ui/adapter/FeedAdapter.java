package com.maria.gallery.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.maria.gallery.R;
import com.maria.gallery.mvp.model.network.OAuth;
import com.maria.gallery.mvp.model.entity.Image;

import java.util.ArrayList;
import java.util.List;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Image> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public FeedAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Image entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(entity);
        notifyItemInserted(items.size() - 1);
    }

    public void updateItems(List<Image> images) {
        if (items == null) {
            return;
        }
        items.clear();
        items.addAll(images);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String fileDownloadLink);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgView;

        ViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgView);
            imgView.setOnClickListener(this);
        }

        void bindData(final Image image) {
            if (!badFile(image)) {
                Glide.with(itemView.getContext())
                        .load(new GlideUrl(image.getPreviewDownloadLink(),
                                        new LazyHeaders.Builder()
                                                .addHeader("Authorization", "OAuth " + OAuth.getToken())
                                                .build()
                                )
                        )
                        .apply(RequestOptions.placeholderOf(R.drawable.image_24).centerCrop())
                        .into(imgView);

                imgView.setClickable(true);
            } else {
                /*
                Если файл порченный
                 */
                imgView.setImageResource(R.drawable.error_24);
                imgView.setClickable(false);
            }
        }

        private boolean badFile(Image image) {
            return image.getPreviewDownloadLink() == null;
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                itemClick(items.get(position));
            }
        }
    }

    private void itemClick(Image image) {
        onItemClickListener.onItemClick(image.getFileDownloadLink());
    }
}
