package com.timthebrick.tinystorage.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UUIDHelper {

    public static List<String> getStringFromMap(HashMap<UUID, String> map) {
        List<String> values = new ArrayList<String>();
        for (UUID id : map.keySet()) {
            values.add(map.get(id));
        }
        return values;
    }
}
