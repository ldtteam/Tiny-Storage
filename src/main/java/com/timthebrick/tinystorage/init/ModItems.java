package com.timthebrick.tinystorage.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.*;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModItems {
	public static List<Item> tinyStorageItems = new ArrayList<Item>();
	
	public static Item itemStorageUpgrade = new ItemStorageComponent();
	public static Item itemChestFilter = new ItemChestFilter();
	public static Item itemChestLock = new ItemChestLock();
	public static Item itemDebugTool = new ItemDebugTool();
	
	public static void init(){
		TinyStorageLog.info("Initialising Items");
		
		registerItem(itemStorageUpgrade, Names.Items.STORAGE_COMPONENT);
		registerItem(itemChestFilter, Names.Items.CHEST_FILTER);
		registerItem(itemChestLock, Names.Items.CHEST_LOCK);
		registerItem(itemDebugTool, Names.Items.DEBUG_TOOL);
	}
	
	private static void registerItem(Item item, String name){
		TinyStorageLog.info("Attempting to register item: " + item.getUnlocalizedName());
		try {
			GameRegistry.registerItem(item, name);
			tinyStorageItems.add(item);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

}
