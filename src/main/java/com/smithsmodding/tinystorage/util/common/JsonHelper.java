package com.smithsmodding.tinystorage.util.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.UUID;

public class JsonHelper {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    private static final Type TT_mapUUIDString = new TypeToken<HashMap<UUID, String>>() {
    }.getType();

    public static HashMap<UUID, String> jsonToMapStringString(String json) {
        HashMap<UUID, String> ret = new HashMap<UUID, String>();
        if (json == null || json.isEmpty()) {
            return ret;
        }
        return gson.fromJson(json, TT_mapUUIDString);
    }

    public static String mapUUIDStringToJson(HashMap<UUID, String> map) {
        if (map == null) {
            map = new HashMap<UUID, String>();
        }
        return gson.toJson(map);
    }
}
