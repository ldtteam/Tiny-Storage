package com.timthebrick.tinystorage.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.timthebrick.tinystorage.block.*;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.block.*;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModBlocks {

	public static Block blockTinyChestStone = new BlockTinyChest(Material.rock, "Stone", false);
	public static Block blockTinyChestOakLog = new BlockTinyChest(Material.wood, "OakLog", false);
	public static Block blockTinyChestAcaciaLog = new BlockTinyChest(Material.wood, "AcaciaLog", false);
	public static Block blockTinyChestBirchLog = new BlockTinyChest(Material.wood, "BirchLog", false);
	public static Block blockTinyChestJungleLog = new BlockTinyChest(Material.wood, "JungleLog", false);
	public static Block blockTinyChestSpruceLog = new BlockTinyChest(Material.wood, "SpruceLog", false);
	public static Block blockTinyChestOakPlank = new BlockTinyChest(Material.wood, "OakPlank", false);
	public static Block blockTinyChestAcaciaPlank = new BlockTinyChest(Material.wood, "AcaciaPlank", false);
	public static Block blockTinyChestBirchPlank = new BlockTinyChest(Material.wood, "BirchPlank", false);
	public static Block blockTinyChestJunglePlank = new BlockTinyChest(Material.wood, "JunglePlank", false);
	public static Block blockTinyChestSprucePlank = new BlockTinyChest(Material.wood, "SprucePlank", false);
	
	public static Block blockTinyChestStoneLocked = new BlockTinyChest(Material.rock, "Stone", true);
	public static Block blockTinyChestOakLogLocked = new BlockTinyChest(Material.wood, "OakLog", true);
	public static Block blockTinyChestAcaciaLogLocked = new BlockTinyChest(Material.wood, "AcaciaLog", true);
	public static Block blockTinyChestBirchLogLocked = new BlockTinyChest(Material.wood, "BirchLog", true);
	public static Block blockTinyChestJungleLogLocked = new BlockTinyChest(Material.wood, "JungleLog", true);
	public static Block blockTinyChestSpruceLogLocked = new BlockTinyChest(Material.wood, "SpruceLog", true);
	public static Block blockTinyChestOakPlankLocked = new BlockTinyChest(Material.wood, "OakPlank", true);
	public static Block blockTinyChestAcaciaPlankLocked = new BlockTinyChest(Material.wood, "AcaciaPlank", true);
	public static Block blockTinyChestBirchPlankLocked = new BlockTinyChest(Material.wood, "BirchPlank", true);
	public static Block blockTinyChestJunglePlankLocked = new BlockTinyChest(Material.wood, "JunglePlank", true);
	public static Block blockTinyChestSprucePlankLocked = new BlockTinyChest(Material.wood, "SprucePlank", true);

	public static Block blockFilterChestStone = new BlockFilterChest(Material.rock, "Stone");
	public static Block blockFilterChestOakLog = new BlockFilterChest(Material.wood, "OakLog");
	public static Block blockFilterChestAcaciaLog = new BlockFilterChest(Material.wood, "AcaciaLog");
	public static Block blockFilterChestBirchLog = new BlockFilterChest(Material.wood, "BirchLog");
	public static Block blockFilterChestJungleLog = new BlockFilterChest(Material.wood, "JungleLog");
	public static Block blockFilterChestSpruceLog = new BlockFilterChest(Material.wood, "SpruceLog");
	public static Block blockFilterChestOakPlank = new BlockFilterChest(Material.wood, "OakPlank");
	public static Block blockFilterChestAcaciaPlank = new BlockFilterChest(Material.wood, "AcaciaPlank");
	public static Block blockFilterChestBirchPlank = new BlockFilterChest(Material.wood, "BirchPlank");
	public static Block blockFilterChestJunglePlank = new BlockFilterChest(Material.wood, "JunglePlank");
	public static Block blockFilterChestSprucePlank = new BlockFilterChest(Material.wood, "SprucePlank");

	public static Block blockDrawOakLog = new BlockDraw(Material.wood, "OakLog");

	public static void init() {
		TinyStorageLog.info("Initialising Blocks");
		
		GameRegistry.registerBlock(blockTinyChestStone, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "Stone");
		GameRegistry.registerBlock(blockTinyChestOakLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "OakLog");
		GameRegistry.registerBlock(blockTinyChestAcaciaLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "AcaciaLog");
		GameRegistry.registerBlock(blockTinyChestBirchLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "BirchLog");
		GameRegistry.registerBlock(blockTinyChestJungleLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "JungleLog");
		GameRegistry.registerBlock(blockTinyChestSpruceLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "SpruceLog");
		GameRegistry.registerBlock(blockTinyChestOakPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "OakPlank");
		GameRegistry.registerBlock(blockTinyChestAcaciaPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "AcaciaPlank");
		GameRegistry.registerBlock(blockTinyChestBirchPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "BirchPlank");
		GameRegistry.registerBlock(blockTinyChestJunglePlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "JunglePlank");
		GameRegistry.registerBlock(blockTinyChestSprucePlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "SprucePlank");
		
		GameRegistry.registerBlock(blockTinyChestStoneLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "Stone");
		GameRegistry.registerBlock(blockTinyChestOakLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "OakLog");
		GameRegistry.registerBlock(blockTinyChestAcaciaLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "AcaciaLog");
		GameRegistry.registerBlock(blockTinyChestBirchLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "BirchLog");
		GameRegistry.registerBlock(blockTinyChestJungleLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "JungleLog");
		GameRegistry.registerBlock(blockTinyChestSpruceLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "SpruceLog");
		GameRegistry.registerBlock(blockTinyChestOakPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "OakPlank");
		GameRegistry.registerBlock(blockTinyChestAcaciaPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "AcaciaPlank");
		GameRegistry.registerBlock(blockTinyChestBirchPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "BirchPlank");
		GameRegistry.registerBlock(blockTinyChestJunglePlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "JunglePlank");
		GameRegistry.registerBlock(blockTinyChestSprucePlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "SprucePlank");

		GameRegistry.registerBlock(blockFilterChestStone, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "Stone");
		GameRegistry.registerBlock(blockFilterChestOakLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "OakLog");
		GameRegistry.registerBlock(blockFilterChestAcaciaLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "AcaciaLog");
		GameRegistry.registerBlock(blockFilterChestBirchLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "BirchLog");
		GameRegistry.registerBlock(blockFilterChestJungleLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "JungleLog");
		GameRegistry.registerBlock(blockFilterChestSpruceLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "SpruceLog");
		GameRegistry.registerBlock(blockFilterChestOakPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "OakPlank");
		GameRegistry.registerBlock(blockFilterChestAcaciaPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "AcaciaPlank");
		GameRegistry.registerBlock(blockFilterChestBirchPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "BirchPlank");
		GameRegistry.registerBlock(blockFilterChestJunglePlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "JunglePlank");
		GameRegistry.registerBlock(blockFilterChestSprucePlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "SprucePlank");

		GameRegistry.registerBlock(blockDrawOakLog, ItemBlockDraw.class, Names.Blocks.DRAW + "OakLog");
	}

}
