package com.timthebrick.tinystorage.reference;

public class Names {

	public static final class Blocks {
		public static final String TINY_CHEST = "tinyChest";
		public static final String FILTER_CHEST = "filterChest";
		public static final String DRAW = "draw";
	}
	
	public static final class Items{
		public static final String STORAGE_UPGRADE = "storageUpgrade";
		public static final String CHEST_FILTER = "chestFilter";
	}

	public static final class NBT {
		public static final String ITEMS = "Items";
		public static final String DISPLAY = "display";
		public static final String COLOR = "color";
		public static final String STATE = "teState";
		public static final String CUSTOM_NAME = "CustomName";
		public static final String DIRECTION = "teDirection";
		public static final String OWNER = "owner";
		public static final String TEXTURE_NAME = "textureName";
	}

	public static final class Containers {
		public static final String VANILLA_INVENTORY = "container.inventory";
		public static final String VANILLA_CRAFTING = "container.crafting";
		public static final String TINY_CHEST = "container.tinystorage:" + Blocks.TINY_CHEST;
		public static final String FILTER_CHEST = "container.tinystorage:" + Blocks.FILTER_CHEST;
		public static final String DRAW = "container.tinystorage:" + Blocks.DRAW;
	}

}
