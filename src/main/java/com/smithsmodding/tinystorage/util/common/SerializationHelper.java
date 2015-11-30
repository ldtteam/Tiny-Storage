package com.smithsmodding.tinystorage.util.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.reference.References;
import cpw.mods.fml.common.FMLCommonHandler;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SerializationHelper {

    private static File playerDataDirectory;

    public static File getPlayerDataDirectory() {
        return playerDataDirectory;
    }

    public static void initModDataDirectories() {
        playerDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + References.MOD_ID.toLowerCase());
        playerDataDirectory.mkdirs();
    }

    public static void writePlayerFriendsListToFile(File directory, String fileName, HashMap<UUID, String> friendsMap) {
        if (directory != null && fileName != null) {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (friendsMap != null) {
                try {
                    File file1 = new File(directory, fileName + ".tmp");
                    File file2 = new File(directory, fileName);
                    FileWriter writer = new FileWriter(file1);
                    writer.write(JsonHelper.mapUUIDStringToJson(friendsMap));
                    writer.close();
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file1.renameTo(file2);
                    //TinyStorageLog.info("Successfully saved Global Friends List to file " + file2.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    TinyStorageLog.error(e);
                }

            }
        }
    }

    public static HashMap<UUID, String> readPlayerFriendsListFromFile(File directory, String fileName) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return readPlayerFriendsListFromFile(new File(directory, fileName));
    }

    public static HashMap<UUID, String> readPlayerFriendsListFromFile(File file) {
        HashMap<UUID, String> returnMap = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            returnMap = JsonHelper.jsonToMapStringString(stringBuilder.toString());
            //TinyStorageLog.info("Successfully read Global Friends List from file " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            TinyStorageLog.error(e);
        }
        return returnMap;
    }
}
