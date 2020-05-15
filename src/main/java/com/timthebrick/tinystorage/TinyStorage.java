package com.timthebrick.tinystorage;

import com.timthebrick.tinystorage.common.core.ClientSetup;
import com.timthebrick.tinystorage.common.core.Config;
import com.timthebrick.tinystorage.common.core.ModSetup;
import com.timthebrick.tinystorage.common.init.Registration;
import com.timthebrick.tinystorage.common.proxy.ClientProxy;
import com.timthebrick.tinystorage.common.proxy.IProxy;
import com.timthebrick.tinystorage.common.proxy.ServerProxy;
import com.timthebrick.tinystorage.common.reference.References;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(References.MOD_ID)
public class TinyStorage {

    private static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public TinyStorage(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Registration.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }

}
