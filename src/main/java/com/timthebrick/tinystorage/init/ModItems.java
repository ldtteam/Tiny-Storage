package com.timthebrick.tinystorage.init;

import net.minecraft.item.Item;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.*;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModItems {
	
	public static Item itemStorageUpgrade = new ItemStorageComponent();
	public static Item itemChestFilter = new ItemChestFilter();
	
	public static void init(){
		TinyStorageLog.info("Initialising Items");
		
		GameRegistry.registerItem(itemStorageUpgrade, Names.Items.STORAGE_COMPONENT);
		GameRegistry.registerItem(itemChestFilter, Names.Items.CHEST_FILTER);
	}

}
