package com.maria.gallery.mvp.model.network;

public class OAuth {
    private static String token;

    public static void setToken(String token) {
        if (token == null) {
            return;
        }
        if (token.equals("")) {
            return;
        }
        OAuth.token = token;
    }

    public static String getToken() {
        if (token != null) {
            return token;
        }
        return "";
    }
}
