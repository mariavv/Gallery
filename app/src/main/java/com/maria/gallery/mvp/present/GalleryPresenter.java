package com.maria.gallery.mvp.present;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maria.gallery.mvp.model.FeedRepo;
import com.maria.gallery.mvp.model.File;
import com.maria.gallery.mvp.model.Image;
import com.maria.gallery.mvp.model.ImagesRow;
import com.maria.gallery.mvp.model.ImagesRow2;
import com.maria.gallery.mvp.view.GalleryView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private final FeedRepo feedRepo = new FeedRepo();

    public void onCreateActivity() {
        feedRepo.getFeed()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(images -> {
                            //getViewState().fillFeed(images);
                            getViewState().fillFeed(images);
                            //String s = images.get(0).getFileDownloadLink();
                        }, throwable -> {
                            getViewState().errorGetFeed(throwable);
                        }
                );
    }

    public void parseFeed(List<Image> images) {
        List<ImagesRow> imageRows = new ArrayList<>();

        int i = 0;
        while (i < images.size() - 1) {
            ImagesRow row = new ImagesRow(images.get(i++).getPic(), images.get(i++).getPic());
            imageRows.add(row);
        }
        if (images.size() % 2 == 1) {
            ImagesRow row = new ImagesRow(images.get(i).getPic(), 0);
            imageRows.add(row);
        }

        getViewState().onRowsSet(imageRows);
    }

    public void parse(List<File> images) {
        List<ImagesRow2> imageRows = new ArrayList<>();

        int i = 0;
        while (i < images.size() - 1) {
            ImagesRow2 row = new ImagesRow2(images.get(i++), images.get(i++));
            imageRows.add(row);
        }
        if (images.size() % 2 == 1) {
            ImagesRow2 row = new ImagesRow2(images.get(i), null);
            imageRows.add(row);
        }

        getViewState().onRowsSet2(imageRows);
    }
}
