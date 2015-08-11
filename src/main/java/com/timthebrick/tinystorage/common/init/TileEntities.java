package com.timthebrick.tinystorage.common.init;

import com.timthebrick.tinystorage.common.tileentity.implementations.*;
import net.minecraft.tileentity.TileEntity;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityClayChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityClayChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityClayChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityFilterChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityFilterChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityFilterChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPeacefulChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPeacefulChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPeacefulChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityQuarryChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityTinyChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityTinyChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityTinyChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityVacuumChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityVacuumChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityVacuumChestSmall;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityWoolChestLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityWoolChestMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityWoolChestSmall;

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
		
		registerTileEntityWithAlternatives(TileEntityPeacefulChest.class, "blockPeacefulChest", "tileEntityPeacefulChest");
		registerTileEntityWithAlternatives(TileEntityPeacefulChestSmall.class, "blockPeacefulChestSmall", "tileEntityPeacefulChestSmall");
		registerTileEntityWithAlternatives(TileEntityPeacefulChestMedium.class, "blockPeacefulChestMedium", "tileEntityPeacefulChestMedium");
		registerTileEntityWithAlternatives(TileEntityPeacefulChestLarge.class, "blockPeacefulChestLarge", "tileEntityPeacefulChestLarge");
		
		registerTileEntityWithAlternatives(TileEntityVacuumChest.class, "blockVacuumChest", "tileEntityVacuumChest");
		registerTileEntityWithAlternatives(TileEntityVacuumChestSmall.class, "blockVacuumChestSmall", "tileEntityVacuumChestSmall");
		registerTileEntityWithAlternatives(TileEntityVacuumChestMedium.class, "blockVacuumChestMedium", "tileEntityVacuumChestMedium");
		registerTileEntityWithAlternatives(TileEntityVacuumChestLarge.class, "blockVacuumChestLarge", "tileEntityVacuumChestLarge");
		
		registerTileEntityWithAlternatives(TileEntityClayChest.class, "blockClayChest", "tileEntityClayChest");
		registerTileEntityWithAlternatives(TileEntityClayChestSmall.class, "blockClayChestSmall", "tileEntityClayChestSmall");
		registerTileEntityWithAlternatives(TileEntityClayChestMedium.class, "blockClayChestMedium", "tileEntityClayChestMedium");
		registerTileEntityWithAlternatives(TileEntityClayChestLarge.class, "blockClayChestLarge", "tileEntityClayChestLarge");
		
		registerTileEntityWithAlternatives(TileEntityQuarryChest.class, "blockQuarryChest", "tileEntityQuarryChest");
		registerTileEntityWithAlternatives(TileEntityQuarryChestSmall.class, "blockQuarryChestSmall", "tileEntityQuarryChestSmall");
		registerTileEntityWithAlternatives(TileEntityQuarryChestMedium.class, "blockQuarryChestMedium", "tileEntityQuarryChestMedium");
		registerTileEntityWithAlternatives(TileEntityQuarryChestLarge.class, "blockQuarryChestLarge", "tileEntityQuarryChestLarge");

		registerTileEntity(TileEntityBookCase.class, "tileEntityBookCase");
		registerTileEntity(TileEntityImpossibleChest.class, "tileEntityImpossibleChest");
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
