package com.maria.gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.maria.gallery.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ViewImageActivity extends AppCompatActivity {

    public static final String ARG_FILE_DOWNLOAD_LINK = "fileDownloadLink";

    public static Intent createStartIntent(Context context, String fileDownloadLink) {
        Intent intent = new Intent(context, ViewImageActivity.class);
        Bundle arguments = new Bundle();
        arguments.putString(ARG_FILE_DOWNLOAD_LINK, fileDownloadLink);
        intent.putExtras(arguments);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        ImageView image = findViewById(R.id.image);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(getIntent().getStringExtra(ARG_FILE_DOWNLOAD_LINK))
                .into(image, new ImageLoadedCallback(progressBar) {
                    @Override
                    public void onSuccess() {
                        if (this.progressBar != null) {
                            this.progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        ImageLoadedCallback(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError(Exception e) {

        }
    }
}
