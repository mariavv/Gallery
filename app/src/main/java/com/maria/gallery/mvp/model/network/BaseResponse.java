package com.maria.gallery.mvp.model.network;

import com.google.gson.annotations.SerializedName;

/*
Хранит ответ с сервера в виде локальной модели.
 */

public class BaseResponse<T> {

    @SerializedName("items")
    protected T data;

    public T getData() {
        return data;
    }
}
