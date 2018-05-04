package com.maria.gallery.mvp.model.network;


import android.graphics.drawable.Drawable;

import com.maria.gallery.mvp.model.entity.Image;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
Используется для управления адресом запроса к серверу
 */
public interface RestService {

    String API_URL = "https://cloud-api.yandex.net:443/v1/disk/";

    @GET("resources/last-uploaded")
    Observable<BaseResponse<List<Image>>> getFeed(
            @Query("limit") final int limit,
            @Query("media_type") final String mediaType
    );
}
