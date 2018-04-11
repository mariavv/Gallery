package com.maria.gallery.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.adapter.ImagesRowAdapter;
import com.maria.gallery.mvp.ImagesRow;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.mvp.view.GalleryView;

public class GalleryActivity extends MvpAppCompatActivity implements GalleryView {

    @InjectPresenter
    GalleryPresenter presenter;

    RecyclerView recycler;
    ImagesRowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configureViews();
    }

    private void configureViews() {
        recycler = findViewById(R.id.recycler);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new ImagesRowAdapter();
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground));
        adapter.addItem(new ImagesRow(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
    }
}
