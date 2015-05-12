package com.timthebrick.tinystorage.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import com.timthebrick.tinystorage.block.BlockFilterChest;
import com.timthebrick.tinystorage.block.BlockMicroChest;
import com.timthebrick.tinystorage.block.BlockPeacefulChest;
import com.timthebrick.tinystorage.block.BlockPiggyBank;
import com.timthebrick.tinystorage.block.BlockTinyChest;
import com.timthebrick.tinystorage.block.BlockTrashChest;
import com.timthebrick.tinystorage.block.BlockVacuumChest;
import com.timthebrick.tinystorage.block.BlockWoolChestLarge;
import com.timthebrick.tinystorage.block.BlockWoolChestMedium;
import com.timthebrick.tinystorage.block.BlockWoolChestSmall;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.block.ItemBlockFilterChest;
import com.timthebrick.tinystorage.item.block.ItemBlockMicroChest;
import com.timthebrick.tinystorage.item.block.ItemBlockPeacefulChest;
import com.timthebrick.tinystorage.item.block.ItemBlockPiggyBank;
import com.timthebrick.tinystorage.item.block.ItemBlockTinyChest;
import com.timthebrick.tinystorage.item.block.ItemBlockTrashChest;
import com.timthebrick.tinystorage.item.block.ItemBlockVacuumChest;
import com.timthebrick.tinystorage.item.block.ItemBlockWoolChestLarge;
import com.timthebrick.tinystorage.item.block.ItemBlockWoolChestMedium;
import com.timthebrick.tinystorage.item.block.ItemBlockWoolChestSmall;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModBlocks {
	public static List<Block> tinyStorageBlocks = new ArrayList<Block>();

	// Tiny Chests
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

	// Filter Chests
	public static Block blockFilterChestStone = new BlockFilterChest(Material.rock, "Stone", false);
	public static Block blockFilterChestOakLog = new BlockFilterChest(Material.wood, "OakLog", false);
	public static Block blockFilterChestAcaciaLog = new BlockFilterChest(Material.wood, "AcaciaLog", false);
	public static Block blockFilterChestBirchLog = new BlockFilterChest(Material.wood, "BirchLog", false);
	public static Block blockFilterChestJungleLog = new BlockFilterChest(Material.wood, "JungleLog", false);
	public static Block blockFilterChestSpruceLog = new BlockFilterChest(Material.wood, "SpruceLog", false);
	public static Block blockFilterChestOakPlank = new BlockFilterChest(Material.wood, "OakPlank", false);
	public static Block blockFilterChestAcaciaPlank = new BlockFilterChest(Material.wood, "AcaciaPlank", false);
	public static Block blockFilterChestBirchPlank = new BlockFilterChest(Material.wood, "BirchPlank", false);
	public static Block blockFilterChestJunglePlank = new BlockFilterChest(Material.wood, "JunglePlank", false);
	public static Block blockFilterChestSprucePlank = new BlockFilterChest(Material.wood, "SprucePlank", false);

	public static Block blockFilterChestStoneLocked = new BlockFilterChest(Material.rock, "Stone", true);
	public static Block blockFilterChestOakLogLocked = new BlockFilterChest(Material.wood, "OakLog", true);
	public static Block blockFilterChestAcaciaLogLocked = new BlockFilterChest(Material.wood, "AcaciaLog", true);
	public static Block blockFilterChestBirchLogLocked = new BlockFilterChest(Material.wood, "BirchLog", true);
	public static Block blockFilterChestJungleLogLocked = new BlockFilterChest(Material.wood, "JungleLog", true);
	public static Block blockFilterChestSpruceLogLocked = new BlockFilterChest(Material.wood, "SpruceLog", true);
	public static Block blockFilterChestOakPlankLocked = new BlockFilterChest(Material.wood, "OakPlank", true);
	public static Block blockFilterChestAcaciaPlankLocked = new BlockFilterChest(Material.wood, "AcaciaPlank", true);
	public static Block blockFilterChestBirchPlankLocked = new BlockFilterChest(Material.wood, "BirchPlank", true);
	public static Block blockFilterChestJunglePlankLocked = new BlockFilterChest(Material.wood, "JunglePlank", true);
	public static Block blockFilterChestSprucePlankLocked = new BlockFilterChest(Material.wood, "SprucePlank", true);

	// Wool Chests
	public static Block blockWoolChestSmall = new BlockWoolChestSmall(false);
	public static Block blockWoolChestSmallLocked = new BlockWoolChestSmall(true);
	public static Block blockWoolChestMedium = new BlockWoolChestMedium(false);
	public static Block blockWoolChestMediumLocked = new BlockWoolChestMedium(true);
	public static Block blockWoolChestLarge = new BlockWoolChestLarge(false);
	public static Block blockWoolChestLargeLocked = new BlockWoolChestLarge(true);

	// Vacuum Chests
	public static Block blockVacuumChestStone = new BlockVacuumChest(Material.rock, "Stone", false);
	public static Block blockVacuumChestOakLog = new BlockVacuumChest(Material.wood, "OakLog", false);
	public static Block blockVacuumChestAcaciaLog = new BlockVacuumChest(Material.wood, "AcaciaLog", false);
	public static Block blockVacuumChestBirchLog = new BlockVacuumChest(Material.wood, "BirchLog", false);
	public static Block blockVacuumChestJungleLog = new BlockVacuumChest(Material.wood, "JungleLog", false);
	public static Block blockVacuumChestSpruceLog = new BlockVacuumChest(Material.wood, "SpruceLog", false);
	public static Block blockVacuumChestOakPlank = new BlockVacuumChest(Material.wood, "OakPlank", false);
	public static Block blockVacuumChestAcaciaPlank = new BlockVacuumChest(Material.wood, "AcaciaPlank", false);
	public static Block blockVacuumChestBirchPlank = new BlockVacuumChest(Material.wood, "BirchPlank", false);
	public static Block blockVacuumChestJunglePlank = new BlockVacuumChest(Material.wood, "JunglePlank", false);
	public static Block blockVacuumChestSprucePlank = new BlockVacuumChest(Material.wood, "SprucePlank", false);

	public static Block blockVacuumChestStoneLocked = new BlockVacuumChest(Material.rock, "Stone", true);
	public static Block blockVacuumChestOakLogLocked = new BlockVacuumChest(Material.wood, "OakLog", true);
	public static Block blockVacuumChestAcaciaLogLocked = new BlockVacuumChest(Material.wood, "AcaciaLog", true);
	public static Block blockVacuumChestBirchLogLocked = new BlockVacuumChest(Material.wood, "BirchLog", true);
	public static Block blockVacuumChestJungleLogLocked = new BlockVacuumChest(Material.wood, "JungleLog", true);
	public static Block blockVacuumChestSpruceLogLocked = new BlockVacuumChest(Material.wood, "SpruceLog", true);
	public static Block blockVacuumChestOakPlankLocked = new BlockVacuumChest(Material.wood, "OakPlank", true);
	public static Block blockVacuumChestAcaciaPlankLocked = new BlockVacuumChest(Material.wood, "AcaciaPlank", true);
	public static Block blockVacuumChestBirchPlankLocked = new BlockVacuumChest(Material.wood, "BirchPlank", true);
	public static Block blockVacuumChestJunglePlankLocked = new BlockVacuumChest(Material.wood, "JunglePlank", true);
	public static Block blockVacuumChestSprucePlankLocked = new BlockVacuumChest(Material.wood, "SprucePlank", true);

	// Micro Chests
	public static Block blockMicroChestStone = new BlockMicroChest(Material.rock, "Stone", false);
	public static Block blockMicroChestOakLog = new BlockMicroChest(Material.wood, "OakLog", false);
	public static Block blockMicroChestAcaciaLog = new BlockMicroChest(Material.wood, "AcaciaLog", false);
	public static Block blockMicroChestBirchLog = new BlockMicroChest(Material.wood, "BirchLog", false);
	public static Block blockMicroChestJungleLog = new BlockMicroChest(Material.wood, "JungleLog", false);
	public static Block blockMicroChestSpruceLog = new BlockMicroChest(Material.wood, "SpruceLog", false);
	public static Block blockMicroChestOakPlank = new BlockMicroChest(Material.wood, "OakPlank", false);
	public static Block blockMicroChestAcaciaPlank = new BlockMicroChest(Material.wood, "AcaciaPlank", false);
	public static Block blockMicroChestBirchPlank = new BlockMicroChest(Material.wood, "BirchPlank", false);
	public static Block blockMicroChestJunglePlank = new BlockMicroChest(Material.wood, "JunglePlank", false);
	public static Block blockMicroChestSprucePlank = new BlockMicroChest(Material.wood, "SprucePlank", false);

	public static Block blockMicroChestStoneLocked = new BlockMicroChest(Material.rock, "Stone", true);
	public static Block blockMicroChestOakLogLocked = new BlockMicroChest(Material.wood, "OakLog", true);
	public static Block blockMicroChestAcaciaLogLocked = new BlockMicroChest(Material.wood, "AcaciaLog", true);
	public static Block blockMicroChestBirchLogLocked = new BlockMicroChest(Material.wood, "BirchLog", true);
	public static Block blockMicroChestJungleLogLocked = new BlockMicroChest(Material.wood, "JungleLog", true);
	public static Block blockMicroChestSpruceLogLocked = new BlockMicroChest(Material.wood, "SpruceLog", true);
	public static Block blockMicroChestOakPlankLocked = new BlockMicroChest(Material.wood, "OakPlank", true);
	public static Block blockMicroChestAcaciaPlankLocked = new BlockMicroChest(Material.wood, "AcaciaPlank", true);
	public static Block blockMicroChestBirchPlankLocked = new BlockMicroChest(Material.wood, "BirchPlank", true);
	public static Block blockMicroChestJunglePlankLocked = new BlockMicroChest(Material.wood, "JunglePlank", true);
	public static Block blockMicroChestSprucePlankLocked = new BlockMicroChest(Material.wood, "SprucePlank", true);

	// Misc
	public static Block blockTrashChest = new BlockTrashChest(Material.rock);
	public static Block blockPiggyBank = new BlockPiggyBank(Material.rock);
	public static Block blockPeacefulChest = new BlockPeacefulChest(Material.rock);

	public static void init() {
		TinyStorageLog.info("Initialising Blocks");

		// Tiny Chests
		registerBlock(blockTinyChestStone, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "Stone");
		registerBlock(blockTinyChestOakLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "OakLog");
		registerBlock(blockTinyChestAcaciaLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "AcaciaLog");
		registerBlock(blockTinyChestBirchLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "BirchLog");
		registerBlock(blockTinyChestJungleLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "JungleLog");
		registerBlock(blockTinyChestSpruceLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "SpruceLog");
		registerBlock(blockTinyChestOakPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "OakPlank");
		registerBlock(blockTinyChestAcaciaPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "AcaciaPlank");
		registerBlock(blockTinyChestBirchPlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "BirchPlank");
		registerBlock(blockTinyChestJunglePlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "JunglePlank");
		registerBlock(blockTinyChestSprucePlank, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "SprucePlank");

		registerBlock(blockTinyChestStoneLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "Stone");
		registerBlock(blockTinyChestOakLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "OakLog");
		registerBlock(blockTinyChestAcaciaLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "AcaciaLog");
		registerBlock(blockTinyChestBirchLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "BirchLog");
		registerBlock(blockTinyChestJungleLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "JungleLog");
		registerBlock(blockTinyChestSpruceLogLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "SpruceLog");
		registerBlock(blockTinyChestOakPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "OakPlank");
		registerBlock(blockTinyChestAcaciaPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "AcaciaPlank");
		registerBlock(blockTinyChestBirchPlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "BirchPlank");
		registerBlock(blockTinyChestJunglePlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "JunglePlank");
		registerBlock(blockTinyChestSprucePlankLocked, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST_LOCKED + "SprucePlank");

		// Filter Chests
		registerBlock(blockFilterChestStone, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "Stone");
		registerBlock(blockFilterChestOakLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "OakLog");
		registerBlock(blockFilterChestAcaciaLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "AcaciaLog");
		registerBlock(blockFilterChestBirchLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "BirchLog");
		registerBlock(blockFilterChestJungleLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "JungleLog");
		registerBlock(blockFilterChestSpruceLog, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "SpruceLog");
		registerBlock(blockFilterChestOakPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "OakPlank");
		registerBlock(blockFilterChestAcaciaPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "AcaciaPlank");
		registerBlock(blockFilterChestBirchPlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "BirchPlank");
		registerBlock(blockFilterChestJunglePlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "JunglePlank");
		registerBlock(blockFilterChestSprucePlank, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST + "SprucePlank");

		registerBlock(blockFilterChestStoneLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "Stone");
		registerBlock(blockFilterChestOakLogLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "OakLog");
		registerBlock(blockFilterChestAcaciaLogLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "AcaciaLog");
		registerBlock(blockFilterChestBirchLogLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "BirchLog");
		registerBlock(blockFilterChestJungleLogLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "JungleLog");
		registerBlock(blockFilterChestSpruceLogLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "SpruceLog");
		registerBlock(blockFilterChestOakPlankLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "OakPlank");
		registerBlock(blockFilterChestAcaciaPlankLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "AcaciaPlank");
		registerBlock(blockFilterChestBirchPlankLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "BirchPlank");
		registerBlock(blockFilterChestJunglePlankLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "JunglePlank");
		registerBlock(blockFilterChestSprucePlankLocked, ItemBlockFilterChest.class, Names.Blocks.FILTER_CHEST_LOCKED + "SprucePlank");

		// Wool Chests
		registerBlock(blockWoolChestSmall, ItemBlockWoolChestSmall.class, Names.Blocks.WOOL_CHEST + "Small");
		registerBlock(blockWoolChestSmallLocked, ItemBlockWoolChestSmall.class, Names.Blocks.WOOL_CHEST_LOCKED + "Small");
		registerBlock(blockWoolChestMedium, ItemBlockWoolChestMedium.class, Names.Blocks.WOOL_CHEST + "Medium");
		registerBlock(blockWoolChestMediumLocked, ItemBlockWoolChestMedium.class, Names.Blocks.WOOL_CHEST_LOCKED + "Medium");
		registerBlock(blockWoolChestLarge, ItemBlockWoolChestLarge.class, Names.Blocks.WOOL_CHEST + "Large");
		registerBlock(blockWoolChestLargeLocked, ItemBlockWoolChestLarge.class, Names.Blocks.WOOL_CHEST_LOCKED + "Large");
		
		// Vacuum Chests
		registerBlock(blockVacuumChestStone, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "Stone");
		registerBlock(blockVacuumChestOakLog, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "OakLog");
		registerBlock(blockVacuumChestAcaciaLog, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "AcaciaLog");
		registerBlock(blockVacuumChestBirchLog, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "BirchLog");
		registerBlock(blockVacuumChestJungleLog, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "JungleLog");
		registerBlock(blockVacuumChestSpruceLog, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "SpruceLog");
		registerBlock(blockVacuumChestOakPlank, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "OakPlank");
		registerBlock(blockVacuumChestAcaciaPlank, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "AcaciaPlank");
		registerBlock(blockVacuumChestBirchPlank, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "BirchPlank");
		registerBlock(blockVacuumChestJunglePlank, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "JunglePlank");
		registerBlock(blockVacuumChestSprucePlank, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST + "SprucePlank");
		
		registerBlock(blockVacuumChestStoneLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "Stone");
		registerBlock(blockVacuumChestOakLogLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "OakLog");
		registerBlock(blockVacuumChestAcaciaLogLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "AcaciaLog");
		registerBlock(blockVacuumChestBirchLogLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "BirchLog");
		registerBlock(blockVacuumChestJungleLogLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "JungleLog");
		registerBlock(blockVacuumChestSpruceLogLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "SpruceLog");
		registerBlock(blockVacuumChestOakPlankLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "OakPlank");
		registerBlock(blockVacuumChestAcaciaPlankLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "AcaciaPlank");
		registerBlock(blockVacuumChestBirchPlankLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "BirchPlank");
		registerBlock(blockVacuumChestJunglePlankLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "JunglePlank");
		registerBlock(blockVacuumChestSprucePlankLocked, ItemBlockVacuumChest.class, Names.Blocks.VACUUM_CHEST_LOCKED + "SprucePlank");

		// Micro Chests
		registerBlock(blockMicroChestStone, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "Stone");
		registerBlock(blockMicroChestOakLog, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "OakLog");
		registerBlock(blockMicroChestAcaciaLog, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "AcaciaLog");
		registerBlock(blockMicroChestBirchLog, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "BirchLog");
		registerBlock(blockMicroChestJungleLog, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "JungleLog");
		registerBlock(blockMicroChestSpruceLog, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "SpruceLog");
		registerBlock(blockMicroChestOakPlank, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "OakPlank");
		registerBlock(blockMicroChestAcaciaPlank, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "AcaciaPlank");
		registerBlock(blockMicroChestBirchPlank, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "BirchPlank");
		registerBlock(blockMicroChestJunglePlank, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "JunglePlank");
		registerBlock(blockMicroChestSprucePlank, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST + "SprucePlank");

		registerBlock(blockMicroChestStoneLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "Stone");
		registerBlock(blockMicroChestOakLogLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "OakLog");
		registerBlock(blockMicroChestAcaciaLogLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "AcaciaLog");
		registerBlock(blockMicroChestBirchLogLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "BirchLog");
		registerBlock(blockMicroChestJungleLogLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "JungleLog");
		registerBlock(blockMicroChestSpruceLogLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "SpruceLog");
		registerBlock(blockMicroChestOakPlankLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "OakPlank");
		registerBlock(blockMicroChestAcaciaPlankLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "AcaciaPlank");
		registerBlock(blockMicroChestBirchPlankLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "BirchPlank");
		registerBlock(blockMicroChestJunglePlankLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "JunglePlank");
		registerBlock(blockMicroChestSprucePlankLocked, ItemBlockMicroChest.class, Names.Blocks.MICRO_CHEST_LOCKED + "SprucePlank");

		// Misc
		registerBlock(blockTrashChest, ItemBlockTrashChest.class, Names.Blocks.TRASH_CHEST);
		registerBlock(blockPiggyBank, ItemBlockPiggyBank.class, Names.Blocks.PIGGY_BANK);
		registerBlock(blockPeacefulChest, ItemBlockPeacefulChest.class, Names.Blocks.PEACEFUL_CHEST);
	}

	/**
	 * Register a block with the world, with the specified item class and block
	 * name
	 * 
	 * @param block
	 *            The block to register
	 * @param itemclass
	 *            The item type to register with it : null registers a block
	 *            without associated item.
	 * @param name
	 *            The mod-unique name to register it as, will get prefixed by
	 *            your modid.
	 */
	private static void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name) {
		TinyStorageLog.info("Attempting to register block: " + block.getUnlocalizedName());
		try {
			GameRegistry.registerBlock(block, itemclass, name);
			tinyStorageBlocks.add(block);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

	/**
	 * Register a block with the specified mod specific name
	 * 
	 * @param block
	 *            The block to register
	 * @param name
	 *            The mod-unique name to register it as, will get prefixed by
	 *            your modid.
	 */
	private static void registerBlock(Block block, String name) {
		TinyStorageLog.info("Attempting to register block: " + block.getUnlocalizedName());
		try {
			GameRegistry.registerBlock(block, name);
			tinyStorageBlocks.add(block);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

}
