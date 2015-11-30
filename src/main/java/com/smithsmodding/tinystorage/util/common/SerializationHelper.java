package com.smithsmodding.tinystorage.util.common;

import com.smithsmodding.tinystorage.common.reference.References;
import cpw.mods.fml.common.FMLCommonHandler;

import java.io.File;

public class SerializationHelper {

    private static File playerDataDirectory;

    private static File getPlayerDataDirectory(){
        return playerDataDirectory;
    }

    private static void initModDataDirectories(){
        playerDataDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + References.MOD_ID.toLowerCase());
        playerDataDirectory.mkdirs();
    }

}
