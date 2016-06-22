package com.smithsmodding.tinystorage;

import com.google.common.base.Stopwatch;
import com.smithsmodding.tinystorage.common.proxy.IProxy;
import com.smithsmodding.tinystorage.common.reference.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Tim on 19/06/2016.
 */
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class TinyStorage {

    @Mod.Instance(References.MOD_ID)
    public static TinyStorage instance;

    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static Side side;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
//        TinyStorageLog.info("Staring server init");
//        TinyStorageInitaliser.serverStarting(event);
//        TinyStorageLog.info("Finished server init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
//        TinyStorageLog.info("Staring server stopping");
//        TinyStorageInitaliser.serverStopping(event);
//        TinyStorageLog.info("Finished server stopping after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        //TinyStorageLog.info("Starting pre init - Preparing to store all the things!");
        proxy.preInit();
        //TinyStorageInitaliser.preInit(event);
        //TinyStorageLog.info("Finished pre init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still storing all the things!");
        watch.stop();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        //TinyStorageLog.info("Starting init - Continuing to store all the things!");
        proxy.init();
        //TinyStorageInitaliser.init(event);
        //TinyStorageLog.info("Finished init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Still!? storing all the things!");
        watch.stop();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        //TinyStorageLog.info("Starting post init - Nearly there now!");
        proxy.postInit();
        //TinyStorageInitaliser.postInit(event);
        //TinyStorageLog.info("Finished post init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms - Finally finished storing all the things!");
        //TinyStorageLog.info("Loaded Tiny Storage");
        watch.stop();
    }
}
