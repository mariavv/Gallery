package com.maria.gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.maria.gallery.R;
import com.maria.gallery.mvp.present.ShowImagePresenter;
import com.maria.gallery.mvp.view.ShowImageView;

public class ShowImageActivity extends MvpAppCompatActivity implements ShowImageView {

    public static final String ARG_FILE_DOWNLOAD_LINK = "fileDownloadLink";

    private static final String KEY_TURN = "turn";

    private static final Boolean DEFOULT = false;

    @InjectPresenter
    ShowImagePresenter presenter;

    SubsamplingScaleImageView image;
    ProgressBar progressBar;

    /*
    признак пересоздания активити
    */
    private Boolean turn;

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

        turn = savedInstanceState != null && savedInstanceState.getBoolean(KEY_TURN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        image = findViewById(R.id.image);
        progressBar = findViewById(R.id.imgProgressBar);

        showImage();
    }

    public void showImage() {
        progressBar.setVisibility(View.VISIBLE);

        presenter.getImage(this, getIntent().getStringExtra(ARG_FILE_DOWNLOAD_LINK));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadEror() {
        setImage(ImageSource.resource(R.drawable.error_24));
    }

    @Override
    public void onLoadSuccsess(Bitmap bitmap) {
        setImage(ImageSource.bitmap(bitmap));
    }

    private void setImage(ImageSource source) {
        stopProgressBar();
        if (!turn) {
            image.setImage(source);
        } else {
            turn = DEFOULT;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_TURN, true);
    }
}
