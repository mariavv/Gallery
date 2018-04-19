package com.maria.gallery.mvp.model;

import java.util.List;

import io.reactivex.Observable;

public class FeedRepo {

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    public Observable<List<File>> getFeed() {
        return restService.getFeed().map(BaseResponse::getData);
    }

    /*public Observable<List<Image>> getFeed() {
        return Observable.defer(() -> {
                    List<Image> images = new ArrayList<>();

                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));

                    return Observable.just(images);
                }
        );
    }*/
}
