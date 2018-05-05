package com.maria.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;

import com.maria.gallery.tool.SaveDataHelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaveDataHelperTest {

    @Test
    public void saveAndReadValues() {
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("test", 0);
        sharedPreferences.edit().clear().commit();

        String token = "qwertyuiopasdfghjklzxcvbnm";
        SaveDataHelper.saveToken(token, context);

        String savedToken = SaveDataHelper.getToken(context);
        assertEquals(token, savedToken);
    }
}
