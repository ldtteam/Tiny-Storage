package com.timthebrick.tinystorage;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.init.*;
import com.timthebrick.tinystorage.common.proxy.IProxy;
import com.timthebrick.tinystorage.common.reference.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class TinyStorage {

    public boolean developmentEnvironment;

    @Instance(References.MOD_ID)
    public static TinyStorage instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TinyStorageLog.info("Starting pre init - Preparing to store all the things!");
        TinyStorageInitaliser.preInit(event);
        TinyStorageLog.info("Finished pre init - Still storing all the things!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        TinyStorageLog.info("Starting init - Continuing to store all the things!");
        TinyStorageInitaliser.init(event);
        TinyStorageLog.info("Finished init - Still!? storing all the things!");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        TinyStorageLog.info("Starting post init - Nearly there now!");
        TinyStorageInitaliser.postInit(event);
        TinyStorageLog.info("Finished post init - Finally finished storing all the things!");
    }

}
