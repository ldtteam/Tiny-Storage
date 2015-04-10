package com.timthebrick.tinystorage.init;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.tileentity.TileEntityDraw;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChestLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChestMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChestSmall;
import com.timthebrick.tinystorage.tileentity.TileEntityMicroChest;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBank;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankSmall;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestSmall;
import com.timthebrick.tinystorage.tileentity.TileEntityTrashChest;
import com.timthebrick.tinystorage.tileentity.TileEntityWoolChest;
import com.timthebrick.tinystorage.tileentity.TileEntityWoolChestLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityWoolChestMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityWoolChestSmall;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {

	public static void init() {
		TinyStorageLog.info("Initialising Tile Entities");

		GameRegistry.registerTileEntityWithAlternatives(TileEntityTinyChest.class, "blockTinyChest", "tileEntityTinyChest");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityTinyChestSmall.class, "blockTinyChestSmall", "tileEntityTinyChestSmall");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityTinyChestMedium.class, "blockTinyChestMedium", "tileEntityTinyChestMedium");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityTinyChestLarge.class, "blockTinyChestLarge", "tileEntityTinyChestLarge");

		GameRegistry.registerTileEntityWithAlternatives(TileEntityFilterChest.class, "blockFilterChest", "tileEntityFilterChest");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityFilterChestSmall.class, "blockFilterChestSmall", "tileEntityFilterChestSmall");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityFilterChestMedium.class, "blockFilterChestMedium", "tileEntityFilterChestMedium");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityFilterChestLarge.class, "blockFilterChestLarge", "tileEntityFilterChestLarge");

		GameRegistry.registerTileEntityWithAlternatives(TileEntityWoolChest.class, "blockWoolChest", "tileEntityWoolChest");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityWoolChestSmall.class, "blockWoolChestSmall", "tileEntityWoolChestSmall");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityWoolChestMedium.class, "blockWoolChestMedium", "tileEntityWoolChestMedium");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityWoolChestLarge.class, "blockWoolChestLarge", "tileEntityWoolChestLarge");
		
		GameRegistry.registerTileEntity(TileEntityMicroChest.class, "tileEntityMicroChest");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityDraw.class, "tileEntityDraw");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityTrashChest.class, "tileEntityTrashChest");
		
		GameRegistry.registerTileEntityWithAlternatives(TileEntityPiggyBank.class, "blockPiggyBank", "tileEntityPiggyBank");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityPiggyBankSmall.class, "blockPiggyBankSmall", "tileEntityPiggyBankSmall");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityPiggyBankMedium.class, "blockPiggyBankMedium", "tileEntityPiggyBankMedium");
		GameRegistry.registerTileEntityWithAlternatives(TileEntityPiggyBankLarge.class, "blockPiggyBankLarge", "tileEntityPiggyBankLarge");
	}

}
