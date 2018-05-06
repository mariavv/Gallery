package com.maria.gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.maria.gallery.R;
import com.maria.gallery.mvp.present.ShowImagePresenter;
import com.maria.gallery.mvp.view.ShowImageView;

public class ShowImageActivity extends AppCompatActivity implements ShowImageView {

    public static final String ARG_FILE_DOWNLOAD_LINK = "fileDownloadLink";

    private ShowImagePresenter presenter;

    private SubsamplingScaleImageView image;
    private ProgressBar progressBar;

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
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        presenter = new ShowImagePresenter();
        presenter.attachView(this);

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
            image.setImage(source);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
