package com.smithsmodding.tinystorage.common.init;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.core.UnlocalizedNameDump;
import com.smithsmodding.tinystorage.common.entity.GlobalFriendsListRegistry;
import com.smithsmodding.tinystorage.common.handler.ConfigurationHandler;
import com.smithsmodding.tinystorage.common.handler.CraftingEventHandler;
import com.smithsmodding.tinystorage.common.handler.GuiHandler;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.network.PacketHandler;
import com.smithsmodding.tinystorage.util.common.SerializationHelper;
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
        FMLInterModComms.sendMessage("Waila", "register", "WailaDataProvider.callbackRegister");
    }

    public static void postInit(FMLPostInitializationEvent event) {
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        SerializationHelper.initModDataDirectories();
        GlobalFriendsListRegistry.getInstance().loadAll();
        refreshPlayerUUIDList();
    }

    public static void serverStopping(FMLServerStoppingEvent event) {
        GlobalFriendsListRegistry.getInstance().clear();
        TinyStorage.instance.playerUUIDList.clear();
        TinyStorage.instance.playerUUIDMap.clear();
    }

    public static void refreshPlayerUUIDList() {
        File file = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata");
        List<String> playerUUIDList = new ArrayList<String>();
        HashMap<UUID, String> playerUUIDMap = new HashMap<UUID, String>();
        if (file.isDirectory()) {
            for (File search : file.listFiles()) {
                if (search.getName().contains(".dat")) {
                    try {
                        TinyStorageLog.info("Adding player UUID to list");
                        playerUUIDList.add(search.getName().replaceFirst("[.][^.]+$", ""));
                        playerUUIDMap.put(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", "")), UsernameCache.getLastKnownUsername(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", ""))));
                    } catch (Exception e) {
                        TinyStorageLog.error(e.getStackTrace());
                    }
                }
            }
        }
        TinyStorage.instance.playerUUIDList = playerUUIDList;
        TinyStorage.instance.playerUUIDMap = playerUUIDMap;
    }
}
