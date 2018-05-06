package com.maria.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.maria.gallery.ui.ShowImageActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.support.v4.content.ContextCompat.startActivity;

@RunWith(AndroidJUnit4.class)
public class UiTest {

    @Test
    public void run() {
        Context context = InstrumentationRegistry.getTargetContext();
        Intent intent = ShowImageActivity.createStartIntent(context, "https://downloader.disk.yandex.ru/disk/63a1b4911516899f933142c8e9f5fedd98b0b243533e2ebe846b0899bb8135ca/5aeeac6d/oLN3k9PEJk985a7w1y7waIdNLAEpyj3FuBoZUoOkIBQV9TAVyqd5_hAGrCeKKSVR1VDgxuBNqX3HHRbH6ypZGg%3D%3D?uid=80326057&filename=1202999766_valentiw.jpg&disposition=attachment&hash=&limit=0&content_type=image%2Fjpeg&fsize=24595&hid=79189823e3c6dbd3b270baa07bb81e11&media_type=image&tknv=v2&etag=f1561bc7915a84691f19239a9520a5a7");
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(context, intent, new Bundle());
    }
}
