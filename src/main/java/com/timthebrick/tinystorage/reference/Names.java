package com.timthebrick.tinystorage.reference;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;

public class Names {

	public static final class Blocks {
		public static final String TINY_CHEST = "tinyChest";
		public static final String TINY_CHEST_LOCKED = "tinyChestLocked";
		public static final String FILTER_CHEST = "filterChest";
		public static final String FILTER_CHEST_LOCKED = "filterChestLocked";
		public static final String DRAW = "draw";
		public static final String TRASH_CHEST = "trashChest";
		public static final String MICRO_CHEST = "microChest";
		public static final String MICRO_CHEST_LOCKED = "microChestLocked";
		public static final String WOOL_CHEST = "woolChest";
		public static final String WOOL_CHEST_LOCKED = "woolChestLocked";
		public static final String PIGGY_BANK = "piggyBank";
		public static final String PEACEFUL_CHEST = "peacefulChest";
		public static final String PEACEFUL_CHEST_LOCKED = "peacefulChestLocked";
		public static final String VACUUM_CHEST = "vacuumChest";
		public static final String VACUUM_CHEST_LOCKED = "vacuumChestLocked";
	}
	
	public static final class Items{
		public static final String STORAGE_COMPONENT = "storageComponent";
		public static final String CHEST_FILTER = "chestFilter";
		public static final String CHEST_LOCK = "chestLock";
		public static final String DEBUG_TOOL = "debugTool";
	}

	public static final class NBT {
		public static final String ITEMS = "items";
		public static final String DISPLAY = "display";
		public static final String COLOR = "color";
		public static final String STATE = "teState";
		public static final String CUSTOM_NAME = "customName";
		public static final String DIRECTION = "teDirection";
		public static final String UNIQUE_OWNER = "uniqueOwner";
		public static final String OWNER = "owner";
		public static final String TEXTURE_NAME = "textureName";
		public static final String ACCESS_MODE = "accessMode";
	}

	public static final class Containers {
		public static final String VANILLA_INVENTORY = "container.inventory";
		public static final String VANILLA_CRAFTING = "container.crafting";
		public static final String TINY_CHEST = "container.tinystorage:" + Blocks.TINY_CHEST;
		public static final String TINY_CHEST_LOCKED = "container.tinystorage:" + Blocks.TINY_CHEST_LOCKED;
		public static final String FILTER_CHEST = "container.tinystorage:" + Blocks.FILTER_CHEST;
		public static final String FILTER_CHEST_LOCKED = "container.tinystorage:" + Blocks.FILTER_CHEST_LOCKED;
		public static final String DRAW = "container.tinystorage:" + Blocks.DRAW;
		public static final String TRASH_CHEST = "container.tinystorage:" + Blocks.TRASH_CHEST;
		public static final String MICRO_CHEST = "container.tinystorage:" + Blocks.MICRO_CHEST;
		public static final String MICRO_CHEST_LOCKED = "container.tinystorage:" + Blocks.MICRO_CHEST_LOCKED;
		public static final String WOOL_CHEST = "container.tinystorage:" + Blocks.WOOL_CHEST;
		public static final String WOOL_CHEST_LOCKED = "container.tinystorage:" + Blocks.WOOL_CHEST_LOCKED;
		public static final String PIGGY_BANK = "container.tinystorage:" + Blocks.PIGGY_BANK;
		public static final String PEACEFUL_CHEST = "container.tinystorage:" + Blocks.PEACEFUL_CHEST;
		public static final String PEACEFUL_CHEST_LOCKED = "container.tinystorage:" + Blocks.PEACEFUL_CHEST_LOCKED;
		public static final String VACUUM_CHEST = "container.tinystorage:" + Blocks.VACUUM_CHEST;
		public static final String VACUUM_CHEST_LOCKED = "container.tinystorage:" + Blocks.VACUUM_CHEST_LOCKED;
	}
	
	public static final class ButtonTooltips{
		public static final String ACCESS_MODE_TITLE = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase();
		public static final String ACCESS_MODE_BLOCKED = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.DISABLED.toString().toLowerCase();
		public static final String ACCESS_MODE_INPUT_ONLY = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_ONLY.toString().toLowerCase();
		public static final String ACCESS_MODE_OUTPUT_ONLY = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.OUTPUT_ONLY.toString().toLowerCase();
		public static final String ACCESS_MODE_BOTH = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_OUTPUT.toString().toLowerCase();
	}

}
