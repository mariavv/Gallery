package com.maria.gallery.mvp.model.repository;

import com.maria.gallery.mvp.model.entity.Image;
import com.maria.gallery.mvp.model.network.BaseResponse;
import com.maria.gallery.mvp.model.network.RestService;
import com.maria.gallery.mvp.model.network.RestServiceProvider;

import java.util.List;

import io.reactivex.Observable;

public class FeedRepo {

    private static final int ITEMS_LIMIT = 50;
    private static final String MEDIA_TYPE = "image";

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    public Observable<List<Image>> getFeed() {
        return restService.getFeed(ITEMS_LIMIT, MEDIA_TYPE).map(BaseResponse::getData);
    }
}
