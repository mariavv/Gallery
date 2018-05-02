package com.maria.gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maria.gallery.R;
import com.maria.gallery.mvp.present.ShowImagePresenter;
import com.maria.gallery.mvp.view.ShowImageView;

public class ShowImageActivity extends AppCompatActivity implements ShowImageView {

    public static final String ARG_FILE_DOWNLOAD_LINK = "fileDownloadLink";

    @InjectPresenter
    ShowImagePresenter presenter;

    ImageView image;
    ProgressBar progressBar;

    public static Intent createStartIntent(Context context, String fileDownloadLink) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        Bundle arguments = new Bundle();
        arguments.putString(ARG_FILE_DOWNLOAD_LINK, fileDownloadLink);
        intent.putExtras(arguments);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        image = findViewById(R.id.image);
        progressBar = findViewById(R.id.imgProgressBar);

        showImage();
    }

    public void showImage() {
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(getIntent().getStringExtra(ARG_FILE_DOWNLOAD_LINK))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                })
                .into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
