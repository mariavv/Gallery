package com.maria.gallery.mvp.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceProvider {

    private static final RestServiceProvider INSTANCE = new RestServiceProvider();
    private RestService restService;

    private RestServiceProvider() {}

    public static RestServiceProvider newInstance() {
        return INSTANCE;
    }

    public synchronized final RestService getRestService() {
        if (restService == null) {
            restService = createRestService();
        }
        return restService;
    }

    private RestService createRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestService.API_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return  retrofit.create(RestService.class);
    }

    private OkHttpClient provideClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }
}
