package com.maria.gallery.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.maria.gallery.mvp.model.Image;
import com.maria.gallery.mvp.model.ImagesRow;

import java.util.List;

public interface GalleryView extends MvpView {
    void fillFeed(List<Image> images);

    void errorGetFeed(Throwable throwable);

    void onRowsSet(List<ImagesRow> imageRows);
}
