package com.timthebrick.tinystorage;

import com.google.common.base.Stopwatch;
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
import cpw.mods.fml.relauncher.Side;

import java.util.concurrent.TimeUnit;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class TinyStorage {

    public boolean developmentEnvironment;

    @Instance(References.MOD_ID)
    public static TinyStorage instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static Side side;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting pre init - Preparing to store all the things!");
        proxy.preInit();
        TinyStorageInitaliser.preInit(event);
        TinyStorageLog.info("Finished pre init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still storing all the things!");
        watch.stop();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting init - Continuing to store all the things!");
        proxy.init();
        TinyStorageInitaliser.init(event);
        TinyStorageLog.info("Finished init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still!? storing all the things!");
        watch.stop();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting post init - Nearly there now!");
        proxy.postInit();
        TinyStorageInitaliser.postInit(event);
        TinyStorageLog.info("Finished post init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Finally finished storing all the things!");
        TinyStorageLog.info("Loaded Tiny Storage");
        watch.stop();
    }
}
