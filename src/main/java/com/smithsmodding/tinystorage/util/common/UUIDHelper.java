package com.smithsmodding.tinystorage.util.common;

import com.smithsmodding.tinystorage.TinyStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UUIDHelper {

    public static List<String> getStringListFromMap(HashMap<UUID, String> map) {
        List<String> values = new ArrayList<String>();
        for (UUID id : map.keySet()) {
            values.add(map.get(id));
        }
        return values;
    }

    public static String getNameFromUUIDCompound(String string){
        for(UUID uuid : TinyStorage.instance.playerUUIDMap.keySet()){
            if(string.contains(uuid.toString())){
                return TinyStorage.instance.playerUUIDMap.get(uuid);
            }
        }
        return "";
    }
}
