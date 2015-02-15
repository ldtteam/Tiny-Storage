package com.timthebrick.tinystorage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.handler.GuiHandler;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.init.ModItems;
import com.timthebrick.tinystorage.init.Recipes;
import com.timthebrick.tinystorage.init.TileEntities;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.proxy.CommonProxy;
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

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, guiFactory = References.GUI_FACTORY_CLASS, dependencies = "after:ForgeMultipart")
public class TinyStorage {

	@Instance(References.MOD_ID)
	public static TinyStorage instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		TinyStorageLog.info("Starting pre init");
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		PacketHandler.init();
		
		ModBlocks.init();
		ModItems.init();
		TinyStorageLog.info("Finished pre init");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		TinyStorageLog.info("Starting init");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		TileEntities.init();
		proxy.initRenderingAndTextures();
		proxy.registerEventHandlers();
		Recipes.init();
		TinyStorageLog.info("Finished init");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		TinyStorageLog.info("Starting post init");
		TinyStorageLog.info("Finished post init");
	}

}
