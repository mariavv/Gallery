package com.maria.gallery.mvp.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class File {

    @NonNull
    //@PrimaryKey
    @SerializedName("sha256")
    private String id = "";

    @SerializedName("path")
    private String path;

    @SerializedName("size")
    private long size;

    @SerializedName("preview")
    private String previewDownloadLink;

    @SerializedName("name")
    private String name;

    @SerializedName("file")
    private String fileDownloadLink;

    @SerializedName("created")
    private String date;
}
