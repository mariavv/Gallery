package com.maria.gallary.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maria.gallary.R;
import com.maria.gallary.mvp.present.GallaryPresenter;
import com.maria.gallary.mvp.view.GallaryView;

public class GallaryActivity extends MvpAppCompatActivity implements GallaryView {

    @InjectPresenter
    GallaryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
