package com.smithsmodding.tinystorage.common.init;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.handler.GuiHandler;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
        ModBlocks.init();
        ModItems.init();
        TileEntities.init();
        TinyStorage.instance.proxy.initRenderingAndTextures();
    }

    public static void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TinyStorage.instance, new GuiHandler());
    }

    public static void postInit(FMLPostInitializationEvent event) {
    }
}
