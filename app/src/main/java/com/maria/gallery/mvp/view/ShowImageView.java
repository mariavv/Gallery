package com.maria.gallery.mvp.view;

import android.graphics.Bitmap;

public interface ShowImageView {
    void onLoadEror();

    void onLoadSuccsess(Bitmap bitmap);
}
