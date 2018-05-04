package com.maria.gallery.tool;

import android.content.Context;
import android.preference.PreferenceManager;

public class SaveDataHelper {

    private static final String TOKEN = "TOKEN";

    public static String getToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(TOKEN, null);
    }

    public static void saveToken(String token, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(TOKEN, token)
                .apply();
    }
}
