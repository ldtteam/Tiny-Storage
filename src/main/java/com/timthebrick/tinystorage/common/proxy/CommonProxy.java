package com.timthebrick.tinystorage.common.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.timthebrick.tinystorage.common.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.common.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.common.handler.PlayerEventHandler;
import com.timthebrick.tinystorage.common.handler.TickHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public abstract class CommonProxy implements IProxy {

	public void registerEventHandlers() {
		PlayerEventHandler playerEventHandler = new PlayerEventHandler();
		CraftingEventHandler craftingEventHandler = new CraftingEventHandler();
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(playerEventHandler);
		MinecraftForge.EVENT_BUS.register(craftingEventHandler);
		FMLCommonHandler.instance().bus().register(playerEventHandler);
		FMLCommonHandler.instance().bus().register(craftingEventHandler);
	}
	
	public String getMinecraftVersion() {
		return Loader.instance().getMinecraftModContainer().getVersion();
	}
	
}
