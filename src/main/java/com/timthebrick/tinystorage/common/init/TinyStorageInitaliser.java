package com.timthebrick.tinystorage.common.init;

import com.google.common.base.Stopwatch;
import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.core.UnlocalizedNameDump;
import com.timthebrick.tinystorage.common.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.common.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.common.handler.GuiHandler;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.common.reference.Colours;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.util.colour.Colour;
import com.timthebrick.tinystorage.util.colour.ColourSampler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.item.*;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.UsernameCache;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        doColourMap();
    }

    public static void serverStarting(FMLServerStartingEvent event) {
        refreshPlayerUUIDList(TinyStorage.instance.playerUUIDs);
    }

    public static void serverStopping(FMLServerStoppingEvent event){
        TinyStorage.instance.playerUUIDs.clear();
        TinyStorage.instance.playerList.clear();
    }

    public static void refreshPlayerUUIDList(ArrayList<String> uuidList) {
        File file = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "playerdata");
        if (file.isDirectory()) {
            for (File search : file.listFiles()) {
                if (!uuidList.contains(search.getName().replaceFirst("[.][^.]+$", ""))) {
                    TinyStorageLog.info("Adding player UUID to list");
                    TinyStorage.instance.playerUUIDs.add(search.getName().replaceFirst("[.][^.]+$", ""));
                    TinyStorage.instance.playerList.put(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", "")), UsernameCache.getLastKnownUsername(UUID.fromString(search.getName().replaceFirst("[.][^.]+$", ""))));
                }
            }
        }
    }

    private static void doColourMap() {
        Stopwatch watch = Stopwatch.createStarted();
        Iterator<Item> iterator = Item.itemRegistry.iterator();
        while (iterator.hasNext()) {
            try {
                Item item = iterator.next();
                if (item != null) {
                    if (item instanceof ItemBook || item instanceof ItemEditableBook || item instanceof ItemEnchantedBook || item instanceof ItemWritableBook) {
                        if (Colours.itemColourMap == null) {
                            Colours.itemColourMap = new HashMap<Item, Colour>();
                        }
                        Colour colour = ColourSampler.getColourSampleFromItemStack(new ItemStack(item));
                        Colours.itemColourMap.put(item, colour);
                        TinyStorageLog.info("Registering colour mapping of: " + item.getUnlocalizedName() + " to colour: " + colour.toString());
                    }
                }
            } catch (Exception e) {
                TinyStorageLog.error(e);
            }
        }
        TinyStorageLog.info("Colour mapping done after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }
}
