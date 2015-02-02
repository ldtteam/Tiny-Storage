package com.timthebrick.tinystorage.proxy;

import com.timthebrick.tinystorage.handler.ConfigurationHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {
	
	public void registerRenders() {

	}

	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
	}

}
