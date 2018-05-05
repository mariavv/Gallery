package com.maria.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.maria.gallery.tool.SaveDataHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

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
