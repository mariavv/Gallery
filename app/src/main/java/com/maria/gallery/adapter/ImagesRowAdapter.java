package com.maria.gallery.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maria.gallery.R;
import com.maria.gallery.mvp.model.OAuth;
import com.maria.gallery.mvp.model.data.Image;
import com.maria.gallery.mvp.model.data.ImagesPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ImagesRowAdapter extends RecyclerView.Adapter<ImagesRowAdapter.ViewHolder> {

    private List<ImagesPair> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;
    private cBack cback;
    private Listener listener;


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

    public void addItem(ImagesPair entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(entity);
        notifyItemInserted(items.size() - 1);
    }

    /*public void updateItems(List<ImagesPair> items) {
        if (items == null) {
            return;
        }
        this.items = items;
        notifyDataSetChanged();
    }*/

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void setCBack(cBack cback) {
        this.cback = cback;
    }

    public void configWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String fileDownloadLink);
    }

    public interface cBack {
        void onImageLoaded(ImageView view, Drawable pic) throws IOException;
    }

    public interface Listener {
        void onGetImage(ImageView view, Drawable image);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, cBack {

        ImageView imgLeft, imgRight;

        ViewHolder(View itemView) {
            super(itemView);

            imgLeft = itemView.findViewById(R.id.leftImg);
            imgRight = itemView.findViewById(R.id.rightImg);

            setImgWidth(imgLeft);
            setImgWidth(imgRight);

            setCBack(this);

            imgLeft.setOnClickListener(this);
            imgRight.setOnClickListener(this);
        }

        private void setImgWidth(ImageView img) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) img.getLayoutParams();
            params.width = screenWidth / 2;
            img.requestLayout();
        }

        void bindData(final ImagesPair imagesRow) {
            loadImage(imagesRow.getLeftPic(), imgLeft);
            loadImage(imagesRow.getRightPic(), imgRight);
        }

        private void loadImage(Image image, ImageView view) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(image.getPreviewDownloadLink())
                    .addHeader("authorization", "OAuth " + OAuth.token)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Drawable pic = Drawable.createFromStream(body.byteStream(), "okio.RealBufferedSource");
                        //!!!!!!cback.onImageLoaded(view, pic);
                        //listener.onGetImage(view, pic);
                    }
                }
            });

            Glide.with(itemView.getContext())
                    .load(image.getFileDownloadLink())
                    .apply(RequestOptions.placeholderOf(R.drawable.image_24))
                    .into(view);

            /*OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "OAuth" + OAuth.token)
                                .build();
                        return chain.proceed(newRequest);
                    })
                    .build();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(client))
                    .build();

            picasso.load(image.getPreviewDownloadLink())
                    .placeholder(R.drawable.image_24)
                    .fit()
                    .centerCrop()
                    .into(view);*/

            /*Picasso.get()
                    .load(image.getFileDownloadLink())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .fit()
                    .centerCrop()
                    .into(img);*/
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ImagesPair row = items.get(position);
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

        private void itemClick(Image image) {
            onItemClickListener.onItemClick(image.getFileDownloadLink());
        }

        @Override
        public void onImageLoaded(ImageView view, Drawable pic) {
            /*Drawable pic = Drawable.createFromStream(bytes, "okio.RealBufferedSource");

            Glide.with(itemView.getContext())
                    .load(pic)
                    .apply(RequestOptions.placeholderOf(R.drawable.image_24))
                    .into(view);*/

            listener.onGetImage(view, pic);
        }
    }
}
