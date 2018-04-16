package com.maria.gallery.mvp.model;

import com.maria.gallery.R;
import com.maria.gallery.manager.ImageProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FeedRepo implements ImageProvider.Listener {

    public Observable<List<Image>> getFeed() {
        return Observable.defer(() -> {
                    List<Image> images = new ArrayList<>();

                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    images.add(new Image(R.mipmap.ic_launcher));
                    images.add(new Image(R.drawable.ic_launcher_background));
                    //images.add(new Image(R.mipmap.ic_launcher));

                    return Observable.just(images);
                }
        );
    }
}
