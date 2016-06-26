package com.smithsmodding.tinystorage.common.init;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.api.reference.ModBlocks;
import com.smithsmodding.tinystorage.api.reference.ModItems;
import com.smithsmodding.tinystorage.api.reference.TileEntities;
import com.smithsmodding.tinystorage.common.handler.GuiHandler;
import com.smithsmodding.tinystorage.common.item.ItemModule;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tim on 22/06/2016.
 */
public class TinyStorageInitialiser {

    public static void serverStarting(FMLServerStartingEvent event) {
    }

    public static void serverStopping(FMLServerStoppingEvent event) {
    }

    public static void preInit(FMLPreInitializationEvent event) {
        TinyStorage.instance.developmentEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        if (TinyStorage.instance.developmentEnvironment) {
            TinyStorage.getLogger().info("Development Environment detected; some features may not work the same as in a normal game");
        }
        TinyStorage.instance.proxy.registerEventHandlers();
        TinyStorage.instance.proxy.registerKeyBindings();
        initBlocks();
        initItems();
        initTileEntities();
        TinyStorage.instance.proxy.initRenderingAndTextures();
    }

    public static void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TinyStorage.instance, new GuiHandler());
    }

    public static void postInit(FMLPostInitializationEvent event) {
    }

    public static void initItems() {
        ModItems.itemModule = GameRegistry.register(new ItemModule());
    }

    public static void initBlocks() {

    }

    public static void initTileEntities () {

    }
}
