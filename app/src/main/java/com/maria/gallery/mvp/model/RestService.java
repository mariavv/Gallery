package com.maria.gallery.mvp.model;

import android.preference.PreferenceManager;

import com.maria.gallery.mvp.model.data.File;
import com.maria.gallery.ui.GalleryActivity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestService {

    String AUTH_TOKEN = com.maria.gallery.Dbg.token;
    //String AUTH_TOKEN = (PreferenceManager.getDefaultSharedPreferences(new GalleryActivity())).getString("TOKEN", "");
    String API_URL = "https://cloud-api.yandex.net:443/v1/disk/";

    @Headers("Authorization: OAuth " + AUTH_TOKEN)
    @GET("resources/last-uploaded")
    Observable<BaseResponse<List<File>>> getFeed();
}
