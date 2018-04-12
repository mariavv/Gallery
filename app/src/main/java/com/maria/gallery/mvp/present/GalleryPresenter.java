package com.maria.gallery.mvp.present;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maria.gallery.R;
import com.maria.gallery.manager.ImageProvider;
import com.maria.gallery.mvp.model.FeedRepo;
import com.maria.gallery.mvp.model.Image;
import com.maria.gallery.mvp.model.ImagesRow;
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
                            getViewState().fillFeed(images);
                        }, throwable -> {
                            getViewState().errorGetFeed(throwable);
                        }
                );
    }

    public void parseFeed(List<Image> images) {
        ImagesRow row = new ImagesRow(images.get(1).getPic(), images.get(2).getPic());
        List<ImagesRow> imageRows = new ArrayList<>();

        imageRows.add(row);
        imageRows.add(row);
        imageRows.add(row);

        getViewState().onRowsSet(imageRows);
    }
}
