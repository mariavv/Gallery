package com.maria.gallery.mvp.view;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;
import com.maria.gallery.mvp.model.entity.Image;

import java.util.List;

public interface GalleryView extends MvpView {
    // ошибка получения данных с сервера
    void errorGetFeed(Throwable throwable);

    // отобразить полученную ленту картинок
    void fillFeed(List<Image> images);

    // авторизация яндекс диска
    void startYandexAuth(Intent intent);

    // запрсить ленту картинок у сервера
    void showFeed();

    void showMessage(String message);

    void showMessage(int message);

    // выход из аккаунта и из приложения
    void finish();
}