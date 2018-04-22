package com.maria.gallery.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.maria.gallery.R;
import com.maria.gallery.mvp.model.ImagesRow2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImagesRowAdapter extends RecyclerView.Adapter<ImagesRowAdapter.ViewHolder> {

    private List<ImagesRow2> items = new ArrayList<>();

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

    public void addItem(ImagesRow2 entity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(entity);
        notifyItemInserted(items.size() - 1);
    }

    public void updateItems(List<ImagesRow2> items) {
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

        ImageButton img1, img2;

        ViewHolder(View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.leftImg);
            img2 = itemView.findViewById(R.id.rightImg);

            itemView.setOnClickListener(this);
        }

        void bindData(final ImagesRow2 imagesRow) {

            /*URL newurl = null;
            try {
                newurl = new URL(imagesRow.getPic1().getFileDownloadLink());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                img1.setImageBitmap(BitmapFactory.decodeStream(newurl != null ? newurl.openConnection().getInputStream() : null));
            } catch (IOException e) {
                e.printStackTrace();
            }

            URL newurl2 = null;
            try {
                newurl2 = new URL(imagesRow.getPic2().getFileDownloadLink());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                img2.setImageBitmap(BitmapFactory.decodeStream(newurl2 != null ? newurl2.openConnection().getInputStream() : null));
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            new DownloadImageTask(img1)
                    .execute(imagesRow.getPic1().getFileDownloadLink());
            new DownloadImageTask(img2)
                    .execute(imagesRow.getPic2().getFileDownloadLink());

            //img1.setImageURI(Uri.parse(imagesRow.getPic1().getFileDownloadLink()));
            //img2.setImageURI(Uri.parse(imagesRow.getPic2().getFileDownloadLink()));
            //img2.setImageResource(imagesRow.getPic2());
        }

        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageButton bmImage;

            public DownloadImageTask(ImageButton bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    //Log.e("Ошибка передачи изображения", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
            }
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
