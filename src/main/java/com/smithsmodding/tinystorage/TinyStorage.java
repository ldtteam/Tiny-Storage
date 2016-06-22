package com.smithsmodding.tinystorage;

import com.google.common.base.Stopwatch;
import com.smithsmodding.tinystorage.common.init.TinyStorageInitialiser;
import com.smithsmodding.tinystorage.common.proxy.IProxy;
import com.smithsmodding.tinystorage.common.reference.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tim on 19/06/2016.
 */
@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION)
public class TinyStorage {

    //Instance of the mod used for internal things
    @Mod.Instance(References.MOD_ID)
    public static TinyStorage instance;
    //Proxy registration
    @SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    //Current loaded side
    public static Side side;
    //Logger
    private static Logger logger = LogManager.getLogger(References.MOD_ID);
    //Are we in a development environment?
    public boolean developmentEnvironment;

    public static Logger getLogger() {
        return logger;
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        logger.info("Staring server init");
        TinyStorageInitialiser.serverStarting(event);
        logger.info("Finished server init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        logger.info("Staring server stopping");
        TinyStorageInitialiser.serverStopping(event);
        logger.info("Finished server stopping after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        logger.info("Starting pre init - Preparing to store all the things!");
        proxy.preInit();
        TinyStorageInitialiser.preInit(event);
        logger.info("Finished pre init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        logger.info("Starting init - Continuing to store all the things!");
        proxy.init();
        TinyStorageInitialiser.init(event);
        logger.info("Finished init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        watch.stop();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
        logger.info("Starting post init - Nearly there now!");
        proxy.postInit();
        TinyStorageInitialiser.postInit(event);
        logger.info("Finished post init after: " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        logger.info("Loaded Tiny Storage");
        watch.stop();
    }
}
