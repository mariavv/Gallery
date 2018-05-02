package com.maria.gallery.mvp.model;

import com.maria.gallery.mvp.model.data.Image;

import java.util.List;

import io.reactivex.Observable;

public class FeedRepo {

    private static final int ITEMS_LIMIT = 4;
    private static final String MEDIA_TYPE = "image";

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    public Observable<List<Image>> getFeed() {
        return restService.getFeed(ITEMS_LIMIT, MEDIA_TYPE).map(BaseResponse::getData);
    }
}
