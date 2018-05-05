package com.maria.gallery.mvp.view;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

public interface ShowImageView extends MvpView {
    void onLoadEror();

    void onLoadSuccsess(Bitmap bitmap);
}
