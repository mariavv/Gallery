package com.maria.gallery.mvp.model;


import com.maria.gallery.mvp.model.data.Image;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    //String AUTH_TOKEN = OAuth.token;
    String API_URL = "https://cloud-api.yandex.net:443/v1/disk/";

    //@Headers("Authorization: OAuth " + AUTH_TOKEN)
    @GET("resources/last-uploaded")
    Observable<BaseResponse<List<Image>>> getFeed(
            @Query("limit") final int limit,
            @Query("media_type") final String mediaType
    );

    //@Streaming
    //@GET
    //Observable<Response<ResponseBody>> downloadFile(@Url String fileUrl);
}
