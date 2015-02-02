package com.timthebrick.tinystorage.init;

import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityTinyStorage.class, "tileEntityTinyStorage");
		GameRegistry.registerTileEntity(TileEntityTinyChest.class, "tileEntityTinyChest");
	}
	
}
