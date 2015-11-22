package com.smithsmodding.tinystorage.common.handler;

import java.io.File;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.util.common.ConfigurationHelper;
import com.smithsmodding.tinystorage.util.common.Settings;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.core.VersionChecker;
import com.smithsmodding.tinystorage.common.reference.Messages;

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
			}
			config = new Configuration(configFile);
			loadConfig();
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
		Settings.Sounds.soundMode = ConfigurationHelper.getString(config, Messages.Config.SOUND_MODE, Configuration.CATEGORY_GENERAL, "All", StatCollector.translateToLocal(Messages.Config.SOUND_MODE_COMMENT), new String[]{"All", "Self", "None"}, Messages.Config.SOUND_MODE_LABEL);
		Settings.Blocks.peacefulChestMode = config.getBoolean(Messages.Config.PEACEFUL_CHEST_MODE, Configuration.CATEGORY_GENERAL, false, StatCollector.translateToLocal(Messages.Config.PEACEFUL_CHEST_COMMENT), Messages.Config.PEACEFUL_CHEST_LABEL);
		Settings.Misc.versionCheck = config.getBoolean(Messages.Config.VERSION_CHECK_MODE, Configuration.CATEGORY_GENERAL, true, StatCollector.translateToLocal(Messages.Config.VERSION_CHECK_COMMENT), Messages.Config.VERSION_CHECK_LABEL);
		
		if(Settings.Misc.versionCheck && !TinyStorage.instance.developmentEnvironment){
			VersionChecker.check();
		}
		
		if (config.hasChanged()) {
			config.save();
		}
	}
}
