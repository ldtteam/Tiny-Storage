package com.timthebrick.tinystorage.handler;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.timthebrick.tinystorage.TinyStorage;
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
		if (TinyStorage.instance.developmentEnvironment) {
			TinyStorageLog.info("In a development environment, re-generating default configuration file.");
			if (configFile.exists()) {
				configFile.delete();
				config = new Configuration(configFile);
				loadConfig();
			} else {
				config = new Configuration(configFile);
				loadConfig();
			}
		} else {
			if (config == null) {
				config = new Configuration(configFile);
				loadConfig();
			}
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(References.MOD_ID)) {
			loadConfig();
		}
	}

	private static void loadConfig() {

		Settings.Sounds.soundMode = ConfigurationHelper.getString(config, Messages.Config.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Config.SOUND_MODE_COMMENT), new String[] { "All", "Self", "None" },
				Messages.Config.SOUND_MODE_LABEL);
		Settings.Blocks.peacefulChestMode = config.getBoolean(Messages.Config.PEACEFUL_CHEST_MODE, Configuration.CATEGORY_GENERAL, false, StatCollector.translateToLocal(Messages.Config.PEACEFUL_CHEST_COMMENT), Messages.Config.PEACEFUL_CHEST_LABEL);

		if (config.hasChanged()) {
			config.save();
		}
	}
}
