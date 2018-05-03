package com.maria.gallery.tool;

/*
Параметры экрана
 */

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class WindowHelper {
    public static int screenWidth(WindowManager windowManager) {
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
