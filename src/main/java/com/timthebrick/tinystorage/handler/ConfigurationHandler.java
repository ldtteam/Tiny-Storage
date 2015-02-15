package com.timthebrick.tinystorage.handler;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.Messages;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.util.ConfigurationHelper;
import com.timthebrick.tinystorage.util.Settings;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration config;

	public static void init(File configFile) {
		TinyStorageLog.info("Initialising Configuration Handler");
		if (config == null) {
			config = new Configuration(configFile);
			loadConfig();
		}
	}

	private static void loadConfig() {
		
		Settings.Sounds.soundMode = ConfigurationHelper.getString(config, Messages.Config.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Config.SOUND_MODE_COMMENT), new String[]{"All", "Self", "None"}, Messages.Config.SOUND_MODE_LABEL);

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
