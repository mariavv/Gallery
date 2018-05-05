package com.maria.gallery.mvp.model.repository;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.maria.gallery.mvp.model.entity.Image;

import java.lang.reflect.Type;
import java.util.List;

public class GalleryJsonDeserializer implements JsonDeserializer<List<Image>> {

    private static final String ITEMS = "items";

    @Override
    public List<Image> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new GsonBuilder()
                .create()
                .fromJson(json.getAsJsonObject().get(ITEMS).getAsJsonArray(), new TypeToken<List<Image>>(){}.getType());
    }
}
