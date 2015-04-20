package com.timthebrick.tinystorage.init;

import net.minecraft.tileentity.TileEntity;

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

		registerTileEntityWithAlternatives(TileEntityTinyChest.class, "blockTinyChest", "tileEntityTinyChest");
		registerTileEntityWithAlternatives(TileEntityTinyChestSmall.class, "blockTinyChestSmall", "tileEntityTinyChestSmall");
		registerTileEntityWithAlternatives(TileEntityTinyChestMedium.class, "blockTinyChestMedium", "tileEntityTinyChestMedium");
		registerTileEntityWithAlternatives(TileEntityTinyChestLarge.class, "blockTinyChestLarge", "tileEntityTinyChestLarge");

		registerTileEntityWithAlternatives(TileEntityFilterChest.class, "blockFilterChest", "tileEntityFilterChest");
		registerTileEntityWithAlternatives(TileEntityFilterChestSmall.class, "blockFilterChestSmall", "tileEntityFilterChestSmall");
		registerTileEntityWithAlternatives(TileEntityFilterChestMedium.class, "blockFilterChestMedium", "tileEntityFilterChestMedium");
		registerTileEntityWithAlternatives(TileEntityFilterChestLarge.class, "blockFilterChestLarge", "tileEntityFilterChestLarge");

		registerTileEntityWithAlternatives(TileEntityWoolChest.class, "blockWoolChest", "tileEntityWoolChest");
		registerTileEntityWithAlternatives(TileEntityWoolChestSmall.class, "blockWoolChestSmall", "tileEntityWoolChestSmall");
		registerTileEntityWithAlternatives(TileEntityWoolChestMedium.class, "blockWoolChestMedium", "tileEntityWoolChestMedium");
		registerTileEntityWithAlternatives(TileEntityWoolChestLarge.class, "blockWoolChestLarge", "tileEntityWoolChestLarge");
		
		registerTileEntity(TileEntityMicroChest.class, "tileEntityMicroChest");
		registerTileEntity(TileEntityDraw.class, "tileEntityDraw");
		registerTileEntity(TileEntityTrashChest.class, "tileEntityTrashChest");
		
		registerTileEntityWithAlternatives(TileEntityPiggyBank.class, "blockPiggyBank", "tileEntityPiggyBank");
		registerTileEntityWithAlternatives(TileEntityPiggyBankSmall.class, "blockPiggyBankSmall", "tileEntityPiggyBankSmall");
		registerTileEntityWithAlternatives(TileEntityPiggyBankMedium.class, "blockPiggyBankMedium", "tileEntityPiggyBankMedium");
		registerTileEntityWithAlternatives(TileEntityPiggyBankLarge.class, "blockPiggyBankLarge", "tileEntityPiggyBankLarge");
	}
	
	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id){
		TinyStorageLog.info("Attempting to register Tile Entity: " + tileEntityClass.getName());
		try{
			GameRegistry.registerTileEntity(tileEntityClass, id);
		}catch (Exception e){
			TinyStorageLog.error(e);
		}
	}
	
	private static void registerTileEntityWithAlternatives(Class<? extends TileEntity> tileEntityClass, String id, String... alternatives){
		TinyStorageLog.info("Attempting to register Tile Entity with Alternatives: " + tileEntityClass.getName());
		try{
			GameRegistry.registerTileEntityWithAlternatives(tileEntityClass, id, alternatives);
		}catch (Exception e){
			TinyStorageLog.error(e);
		}
	}

}
