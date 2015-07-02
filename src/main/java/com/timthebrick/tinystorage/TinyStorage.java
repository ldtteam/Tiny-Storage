package com.timthebrick.tinystorage;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.core.UnlocalizedNameDump;
import com.timthebrick.tinystorage.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.handler.GuiHandler;
import com.timthebrick.tinystorage.init.*;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.proxy.IProxy;
import com.timthebrick.tinystorage.reference.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.launchwrapper.Launch;

import java.io.File;

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
