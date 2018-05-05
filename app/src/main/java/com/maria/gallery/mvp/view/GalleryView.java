package com.maria.gallery.mvp.view;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;
import com.maria.gallery.mvp.model.entity.Image;

import java.util.List;

public interface GalleryView extends MvpView {
    void errorGetFeed(Throwable throwable);

    void fillFeed(List<Image> images);

    void startYandexAuth(Intent intent);

    void showFeed();

    void showMessage(String message);
}
