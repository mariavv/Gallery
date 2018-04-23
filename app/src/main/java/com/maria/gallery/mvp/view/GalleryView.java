package com.maria.gallery.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.maria.gallery.mvp.model.data.File;
import com.maria.gallery.mvp.model.data.ImagesRow;

import java.util.List;

public interface GalleryView extends MvpView {
    void errorGetFeed(Throwable throwable);

    void fillFeed(List<File> images);

    void onRowsSet(List<ImagesRow> imageRows);
}
