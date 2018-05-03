package com.maria.gallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.tool.WindowHelper;
import com.maria.gallery.ui.adapter.FeedAdapter;
import com.maria.gallery.mvp.model.entity.Image;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.mvp.view.GalleryView;

import java.util.List;

public class GalleryActivity extends MvpAppCompatActivity
        implements GalleryView, FeedAdapter.OnItemClickListener {

    private static final String KEY_TURN = "TURN";

    @InjectPresenter
    GalleryPresenter presenter;

    ProgressBar progressBar;

    RecyclerView recycler;
    FeedAdapter adapter;

    private Boolean turn;
    private int countViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        turn = savedInstanceState != null && savedInstanceState.getBoolean(KEY_TURN);
        countViews = 1;

        configureViews();

        presenter.login(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void configureViews() {
        progressBar = findViewById(R.id.progressBar);

        recycler = findViewById(R.id.recycler);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new FeedAdapter(this, WindowHelper.screenWidth(getWindowManager()));
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        presenter.activityResult(this, requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 2131165258) {
            progressBar.setVisibility(View.VISIBLE);
            presenter.loadFeed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFeed() {
        progressBar.setVisibility(View.VISIBLE);

        if (!turn) {
            presenter.loadFeed();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillFeed(List<Image> images) {
        int c = 5;
        if (countViews++ < 2) {
            this.progressBar.setVisibility(View.GONE);

            for (Image image : images) {
                adapter.addItem(image);
            }
        }
    }

    @Override
    public void startYandexAuthActivity(Intent intent, int request) {
        startActivityForResult(intent, request);
    }

    @Override
    public void errorGetFeed(Throwable throwable) {
        this.progressBar.setVisibility(View.GONE);
        showMessage(throwable.getMessage());
    }

    @Override
    public void onItemClick(String fileDownloadLink) {
        Intent intent = ShowImageActivity.createStartIntent(this, fileDownloadLink);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_TURN, true);
    }
}
