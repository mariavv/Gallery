package com.maria.gallery.mvp.present;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maria.gallery.mvp.model.FeedRepo;
import com.maria.gallery.mvp.model.data.File;
import com.maria.gallery.mvp.model.data.ImagesRow;
import com.maria.gallery.mvp.view.GalleryView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private final FeedRepo feedRepo = new FeedRepo();

    @SuppressLint("CheckResult")
    public void onCreateActivity() {
        feedRepo.getFeed()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::fillFeed, getViewState()::errorGetFeed
                );
    }

    public void parseFeed(List<File> images) {
        List<ImagesRow> imageRows = new ArrayList<>();

        int i = 0;
        while (i < images.size() - 1) {
            ImagesRow row = new ImagesRow(images.get(i++), images.get(i++));
            imageRows.add(row);
        }
        if (images.size() % 2 == 1) {
            ImagesRow row = new ImagesRow(images.get(i), null);
            imageRows.add(row);
        }

        getViewState().onRowsSet(imageRows);
    }
}
