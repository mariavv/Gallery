package com.maria.gallery.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.mvp.present.ShowImagePresenter;
import com.maria.gallery.mvp.view.ShowImageView;

public class ShowImageActivity extends MvpAppCompatActivity implements ShowImageView {

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

        presenter.getImage(this, image, getIntent().getStringExtra(ARG_FILE_DOWNLOAD_LINK));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
