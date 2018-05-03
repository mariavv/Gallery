package com.maria.gallery.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    private int feedWidth;

    public FeedAdapter(OnItemClickListener onItemClickListener, int feedWidth) {
        this.onItemClickListener = onItemClickListener;
        this.feedWidth = feedWidth;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_images_row, parent, false);

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

    /*public void updateItems(List<Image> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }*/

    public interface OnItemClickListener {
        void onItemClick(String fileDownloadLink);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgView;

        ViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.imgView);

            //setImgWidth();

            imgView.setOnClickListener(this);
        }

        private void setImgWidth() {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imgView.getLayoutParams();
            params.width = feedWidth / 2;
            imgView.requestLayout();
        }

        void bindData(final Image image) {
            Glide.with(itemView.getContext())
                    .load(new GlideUrl(image.getPreviewDownloadLink(),
                                    new LazyHeaders.Builder()
                                            .addHeader("Authorization", "OAuth " + OAuth.token)
                                            .build()
                            )
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.image_24).centerCrop())
                    .into(imgView);
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
