package com.maria.gallery.mvp.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("data")
    protected T data;

    public T getData() {
        return data;
    }
}
