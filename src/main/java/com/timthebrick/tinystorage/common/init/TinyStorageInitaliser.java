package com.timthebrick.tinystorage.common.init;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.core.UnlocalizedNameDump;
import com.timthebrick.tinystorage.common.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.common.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.common.handler.GuiHandler;
import com.timthebrick.tinystorage.common.reference.References;
import com.timthebrick.tinystorage.network.PacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.launchwrapper.Launch;

import java.io.File;

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
}
