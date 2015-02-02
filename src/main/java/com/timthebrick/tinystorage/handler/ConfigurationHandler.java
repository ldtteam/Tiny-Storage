package com.timthebrick.tinystorage.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration config;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
			loadConfig();
		}
	}

	private static void loadConfig() {

		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(References.MOD_ID)) {
			loadConfig();
		}
	}

}
