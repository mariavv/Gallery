package com.maria.gallery.mvp.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestService {

    String AUTH_TOKEN = "0fe587d1d6384c90a70b4da10b53a163";
    String API_URL = "https://cloud-api.yandex.net:443/v1/disk/";

    @Headers("Authorization: OAuth " + AUTH_TOKEN)
    @GET("resources/last-uploaded")
    Observable<BaseResponse<List<File>>> getFeed();
}
