package com.maria.gallery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.adapter.ImagesRowAdapter;
import com.maria.gallery.mvp.model.File;
import com.maria.gallery.mvp.model.Image;
import com.maria.gallery.mvp.model.ImagesRow;
import com.maria.gallery.mvp.model.ImagesRow2;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.mvp.view.GalleryView;
import com.yandex.authsdk.YandexAuthToken;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GalleryActivity extends MvpAppCompatActivity implements GalleryView, ImagesRowAdapter.OnItemClickListener {

    private static final int REQUEST_AUTH = 1;
    public static final String TOKEN = "token";

    @InjectPresenter
    GalleryPresenter presenter;

    RecyclerView recycler;
    ImagesRowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        configureViews();

        //String url = "https://oauth.yandex.ru/authorize?response_type=token&client_id=0fe587d1d6384c90a70b4da10b53a163";
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        //startActivityForResult(AuthActivity.start(this), REQUEST_AUTH);

        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //String token = preferences.getString(TOKEN, null);
        //if (token == null) {
            //startActivityForResult(AuthActivity.start(this), REQUEST_AUTH);
        //}

        presenter.onCreateActivity();
    }

    private void configureViews() {
        recycler = findViewById(R.id.recycler);
        configureRecyclerView();

        adapter.setOnItemClickListener(GalleryActivity.this);
    }

    private void configureRecyclerView() {
        adapter = new ImagesRowAdapter();
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_AUTH:

                return;
        }
    }

    @Override
    public void fillFeed(List<File> images) {
        //presenter.parseFeed(images);
        presenter.parse(images);
    }

    @Override
    public void onRowsSet2(List<ImagesRow2> imageRows) {
        for (ImagesRow2 row : imageRows) {
            adapter.addItem(row);
        }
    }

    @Override
    public void errorGetFeed(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRowsSet(List<ImagesRow> imageRows) {
        for (ImagesRow row : imageRows) {
            //adapter.addItem(row);
        }
    }

    @Override
    public void onItemClick() {
        //Intent intent = piActivity.createStartIntent(getContext(), piId);
        //startActivity(intent);
    }
}
