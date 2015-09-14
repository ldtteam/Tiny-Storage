package com.timthebrick.tinystorage.common.init;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.core.UnlocalizedNameDump;
import com.timthebrick.tinystorage.common.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.common.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.common.handler.GuiHandler;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.network.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.UsernameCache;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TinyStorageInitaliser {

    public static void preInit(FMLPreInitializationEvent event) {
        TinyStorage.instance.developmentEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        if (TinyStorage.instance.developmentEnvironment) {
            TinyStorageLog.info("Development Environment detected; some features may not work the same as in a normal game");
        }
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        PacketHandler.init();
        TinyStorage.instance.proxy.registerKeyBindings();
        ModBlocks.init();
        ModItems.init();
        if (TinyStorage.instance.developmentEnvironment) {
            UnlocalizedNameDump.dumpBlockNames(new File(event.getModConfigurationDirectory(), References.MOD_ID + "_BlockUnlocalizedNames.txt"));
            UnlocalizedNameDump.dumpItemNames(new File(event.getModConfigurationDirectory(), References.MOD_ID + "_ItemUnlocalizedNames.txt"));
        }
    }

    public static void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TinyStorage.instance, new GuiHandler());
        TileEntities.init();
        TinyStorage.instance.proxy.initRenderingAndTextures();
        TinyStorage.instance.proxy.registerEventHandlers();
        CraftingEventHandler.init();
        Recipes.init();
        FMLInterModComms.sendMessage("Waila", "register", "com.timthebrick.tinystorage.common.waila.WailaDataProvider.callbackRegister");
    }

    public static void postInit(FMLPostInitializationEvent event) {
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        refreshPlayerUUIDList();
    }

    public static void serverStopping(FMLServerStoppingEvent event) {
        TinyStorage.instance.playerUUIDList.clear();
        TinyStorage.instance.playerUUIDMap.clear();
    }

    public static void refreshPlayerUUIDList() {
        File file = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata");
        List<String> playerUUIDList = new ArrayList<String>();
        HashMap<UUID, String> playerUUIDMap = new HashMap<UUID, String>();
        if (file.isDirectory()) {
            for (File search : file.listFiles()) {
                TinyStorageLog.info("Adding player UUID to list");
                playerUUIDList.add(search.getName().replaceFirst("[.][^.]+$", ""));
                playerUUIDMap.put(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", "")), UsernameCache.getLastKnownUsername(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", ""))));
            }
        }
        TinyStorage.instance.playerUUIDList = playerUUIDList;
        TinyStorage.instance.playerUUIDMap = playerUUIDMap;
    }
}
