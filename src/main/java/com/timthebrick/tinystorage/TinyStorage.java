package com.timthebrick.tinystorage;

import com.google.common.base.Stopwatch;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.init.*;
import com.timthebrick.tinystorage.common.proxy.IProxy;
import com.timthebrick.tinystorage.common.reference.References;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class TinyStorage {

    public boolean developmentEnvironment;
    public ArrayList<String> playerUUIDs = new ArrayList<String>();
    public HashMap<UUID, String> playerList = new HashMap<UUID, String>();

    @Instance(References.MOD_ID)
    public static TinyStorage instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Staring server init");
        TinyStorageInitaliser.serverStarting(event);
        TinyStorageLog.info("Finished server init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Staring server stopping");
        TinyStorageInitaliser.serverStopping(event);
        TinyStorageLog.info("Finished server stopping after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting pre init - Preparing to store all the things!");
        TinyStorageInitaliser.preInit(event);
        TinyStorageLog.info("Finished pre init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still storing all the things!");
        watch.stop();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting init - Continuing to store all the things!");
        TinyStorageInitaliser.init(event);
        TinyStorageLog.info("Finished init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still!? storing all the things!");
        watch.stop();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        TinyStorageLog.info("Starting post init - Nearly there now!");
        TinyStorageInitaliser.postInit(event);
        TinyStorageLog.info("Finished post init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Finally finished storing all the things!");
        TinyStorageLog.info("Loaded Tiny Storage");
        watch.stop();
    }
}
