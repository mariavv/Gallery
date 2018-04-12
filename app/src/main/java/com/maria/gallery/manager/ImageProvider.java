package com.maria.gallery.manager;

/**
 * Загружает изображения из сети
 */

public class ImageProvider {

    private Listener listener;

    public interface Listener {
    }

    public ImageProvider(Listener listener) {
        this.listener = listener;
    }

    public void getImages() {

    }
}
