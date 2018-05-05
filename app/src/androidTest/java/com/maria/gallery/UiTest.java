package com.maria.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.maria.gallery.ui.ShowImageActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.v4.content.ContextCompat.startActivity;

@RunWith(AndroidJUnit4.class)
public class UiTest {

    @Test
    public void run() {
        Context context = InstrumentationRegistry.getTargetContext();
        Intent intent = ShowImageActivity.createStartIntent(context, "fileDownloadLink");
        //startActivity(intent);
    }
}
