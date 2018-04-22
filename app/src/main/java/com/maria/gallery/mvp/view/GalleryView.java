package com.maria.gallery.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.maria.gallery.mvp.model.File;
import com.maria.gallery.mvp.model.Image;
import com.maria.gallery.mvp.model.ImagesRow;
import com.maria.gallery.mvp.model.ImagesRow2;

import java.util.List;

public interface GalleryView extends MvpView {
    void errorGetFeed(Throwable throwable);

    void onRowsSet(List<ImagesRow> imageRows);

    void fillFeed(List<File> images);

    void onRowsSet2(List<ImagesRow2> imageRows);
}
