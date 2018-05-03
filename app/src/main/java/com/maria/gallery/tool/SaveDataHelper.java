package com.maria.gallery.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveDataHelper {

    private static final String TOKEN = "TOKEN";

    private final SharedPreferences preferences;

    public SaveDataHelper(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getToken() {
        return  preferences.getString(TOKEN, null);
    }

    public void saveToken(String token, Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }
}
