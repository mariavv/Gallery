package com.maria.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.maria.gallery.mvp.model.entity.Image;
import com.maria.gallery.mvp.model.network.OAuth;
import com.maria.gallery.mvp.present.GalleryPresenter;
import com.maria.gallery.tool.SaveDataHelper;
import com.maria.gallery.ui.adapter.FeedAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class SaveDataHelperTest {

    @Test
    public void saveAndReadValues() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", 0);
        sharedPreferences.edit().clear().commit();

        String token = "qwertyuiopasdfghjklzxcvbnm";
        SaveDataHelper.saveToken(token, context);

        String savedToken = SaveDataHelper.getToken(context);
        assertTrue(token.equals(savedToken));
    }
}
