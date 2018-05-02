package com.maria.gallery.mvp.present;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maria.gallery.adapter.ImagesRowAdapter;
import com.maria.gallery.mvp.model.FeedRepo;
import com.maria.gallery.mvp.model.data.Image;
import com.maria.gallery.mvp.model.data.ImagesPair;
import com.maria.gallery.mvp.view.GalleryView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private final FeedRepo feedRepo = new FeedRepo();

    public void onCreateActivity() {
        loadFeed();
    }

    public void parseFeed(List<Image> images) {
        List<ImagesPair> imagesPairs = new ArrayList<>();

        int i = 0;
        while (i < images.size() - 1) {
            ImagesPair row = new ImagesPair(images.get(i++), images.get(i++));
            imagesPairs.add(row);
        }

        /*if (images.size() % 2 == 1) {
            ImagesPair row = new ImagesPair(images.get(i), img);
            imagesPairs.add(row);
        }*/

        getViewState().onRowsSet(imagesPairs);
    }

    public void onSync(ImagesRowAdapter adapter) {
        //loadFeed();
        //TODO adapter.updateDataSet();
    }

    @SuppressLint("CheckResult")
    private void loadFeed() {
        feedRepo.getFeed()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::fillFeed, getViewState()::errorGetFeed);
    }

    @Override
    protected void onFirstViewAttach() {
        int b = this.getAttachedViews().size();
        super.onFirstViewAttach();
        int gb = this.getAttachedViews().size();
     }
}
