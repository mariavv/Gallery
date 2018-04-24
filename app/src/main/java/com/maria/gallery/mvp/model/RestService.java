package com.maria.gallery.mvp.model;


import com.maria.gallery.mvp.model.data.File;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestService {

    //String AUTH_TOKEN = OAuth.token;
    String API_URL = "https://cloud-api.yandex.net:443/v1/disk/";

    //@Headers("Authorization: OAuth " + AUTH_TOKEN)
    @GET("resources/last-uploaded")
    Observable<BaseResponse<List<File>>> getFeed();
}
