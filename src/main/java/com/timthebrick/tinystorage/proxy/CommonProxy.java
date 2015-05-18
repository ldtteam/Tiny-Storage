package com.timthebrick.tinystorage.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.timthebrick.tinystorage.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.handler.CraftingEventHandler;
import com.timthebrick.tinystorage.handler.PlayerEventHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public abstract class CommonProxy implements IProxy {

	public void registerEventHandlers() {
		PlayerEventHandler playerEventHandler = new PlayerEventHandler();
		CraftingEventHandler craftingEventHandler = new CraftingEventHandler();
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		MinecraftForge.EVENT_BUS.register(playerEventHandler);
		MinecraftForge.EVENT_BUS.register(craftingEventHandler);
		FMLCommonHandler.instance().bus().register(playerEventHandler);
		FMLCommonHandler.instance().bus().register(craftingEventHandler);
	}
	
}
