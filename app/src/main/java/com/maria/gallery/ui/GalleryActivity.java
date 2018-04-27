package com.maria.gallery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallery.R;
import com.maria.gallery.adapter.ImagesRowAdapter;
import com.maria.gallery.mvp.model.data.Image;
import com.maria.gallery.mvp.model.data.ImagesPair;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.mvp.view.GalleryView;
import com.maria.gallery.mvp.model.OAuth;
import com.yandex.authsdk.YandexAuthException;
import com.yandex.authsdk.YandexAuthOptions;
import com.yandex.authsdk.YandexAuthSdk;
import com.yandex.authsdk.YandexAuthToken;

import java.util.List;

public class GalleryActivity extends MvpAppCompatActivity
        implements GalleryView, ImagesRowAdapter.OnItemClickListener {

    private static final int REQUEST_LOGIN_SDK = 2;

    public static final String TOKEN = "TOKEN";

    @InjectPresenter
    GalleryPresenter presenter;

    RecyclerView recycler;
    ImagesRowAdapter adapter;

    private YandexAuthSdk sdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        configureViews();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString(TOKEN, null);
        if ((token == null) || (token.length() == 0)) {
            sdk = new YandexAuthSdk(new YandexAuthOptions(this, true));
            startActivityForResult(sdk.createLoginIntent(this, null), REQUEST_LOGIN_SDK);
        } else {
            onHaveToken(token);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void configureViews() {
        recycler = findViewById(R.id.recycler);
        configureRecyclerView();

        adapter.setOnItemClickListener(GalleryActivity.this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        adapter.configWidth(size.x);
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
        if (requestCode == REQUEST_LOGIN_SDK) {
            try {
                final YandexAuthToken yandexAuthToken = sdk.extractToken(resultCode, data);
                if (yandexAuthToken != null) {
                    String token = yandexAuthToken.getValue();
                    saveToken(token);

                    onHaveToken(token);
                }
            } catch (YandexAuthException e) {
                showMessage(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 2131165253) {
            presenter.onSync(adapter);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onHaveToken(String token) {
        OAuth.token = token;
        presenter.onCreateActivity();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    @Override
    public void fillFeed(List<Image> images) {
        presenter.parseFeed(images);
    }

    @Override
    public void onRowsSet(List<ImagesPair> imagesPairRows) {
        for (ImagesPair row : imagesPairRows) {
            adapter.addItem(row);
        }
    }

    @Override
    public void errorGetFeed(Throwable throwable) {
        showMessage(throwable.getMessage());
    }

    @Override
    public void onItemClick(String fileDownloadLink) {
        Intent intent = ViewImageActivity.createStartIntent(this, fileDownloadLink);
        startActivity(intent);
    }
}
