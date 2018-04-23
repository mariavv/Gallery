package com.maria.gallery.mvp.model;

import com.maria.gallery.mvp.model.data.File;

import java.util.List;

import io.reactivex.Observable;

public class FeedRepo {

    private RestService restService = RestServiceProvider.newInstance().getRestService();

    public Observable<List<File>> getFeed() {
        return restService.getFeed().map(BaseResponse::getData);
    }
}
