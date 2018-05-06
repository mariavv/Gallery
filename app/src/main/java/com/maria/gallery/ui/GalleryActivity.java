package com.maria.gallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.ui.adapter.FeedAdapter;
import com.maria.gallery.mvp.model.entity.Image;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.mvp.view.GalleryView;

import java.util.List;

public class GalleryActivity extends MvpAppCompatActivity
        implements GalleryView, FeedAdapter.OnItemClickListener {

    private static final int REQUEST_LOGIN_SDK = 1;

    private static final String KEY_TURN = "TURN";

    @InjectPresenter
    GalleryPresenter presenter;

    ProgressBar progressBar;

    RecyclerView recycler;
    FeedAdapter adapter;

    private Boolean turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        turn = savedInstanceState != null && savedInstanceState.getBoolean(KEY_TURN);

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
        progressBar.setVisibility(View.GONE);

        recycler = findViewById(R.id.recycler);
        configureImageFrameFit();
        configureRecyclerView();
    }

    /*
    Отступ слева у ImageView, которая находится в левом ряду создает полосу у левого края экрана
    Настройка - компенсация отступа, чтобы этой полосы не было видно
     */
    private void configureImageFrameFit() {
        int imgMargin = getResources().getDimensionPixelSize(R.dimen.images_space);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) recycler.getLayoutParams();
        params.leftMargin = -imgMargin - 1;
        recycler.requestLayout();
    }

    private void configureRecyclerView() {
        adapter = new FeedAdapter(this);
        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN_SDK) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            }

            if (resultCode == RESULT_OK) {
                presenter.activityResult(this, resultCode, data);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sync) {
            showFeed();
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
        progressBar.setVisibility(View.GONE);
        adapter.updateItems(images);
    }

    @Override
    public void startYandexAuth(Intent intent) {
        startActivityForResult(intent, REQUEST_LOGIN_SDK);
    }

    @Override
    public void errorGetFeed(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
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

        if (!turn) {
            outState.putBoolean(KEY_TURN, true);
        }
    }
}
