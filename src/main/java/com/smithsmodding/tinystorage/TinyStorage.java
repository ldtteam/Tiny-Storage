package com.smithsmodding.tinystorage;

import com.google.common.base.Stopwatch;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.init.TinyStorageInitaliser;
import com.smithsmodding.tinystorage.common.proxy.IProxy;
import com.smithsmodding.tinystorage.common.reference.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS)
public class TinyStorage {

    public boolean developmentEnvironment;
    public List<String> playerUUIDList = new ArrayList<String>();
    public HashMap<UUID, String> playerUUIDMap = new HashMap<UUID, String>();

    @Instance(References.MOD_ID)
    public static TinyStorage instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    /**
     * The current side that is loaded
     */
    public static Side side;

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
