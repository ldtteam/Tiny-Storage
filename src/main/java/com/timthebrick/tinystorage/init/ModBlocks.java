package com.timthebrick.tinystorage.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import com.timthebrick.tinystorage.block.*;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.item.block.*;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModBlocks {
	public static List<Block> tinyStorageBlocks = new ArrayList<Block>();

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

	public static Block blockDrawOakLog = new BlockDraw(Material.wood, "OakLog");
	public static Block blockTrashChest = new BlockTrashChest(Material.rock);

	public static void init() {
		TinyStorageLog.info("Initialising Blocks");

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

		registerBlock(blockDrawOakLog, ItemBlockDraw.class, Names.Blocks.DRAW + "OakLog");
		registerBlock(blockTrashChest, ItemBlockTrashChest.class, Names.Blocks.TRASH_CHEST);
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
		TinyStorageLog.info("Attempting to register " + block.getUnlocalizedName());
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
		TinyStorageLog.info("Attempting to register " + block.getUnlocalizedName());
		try {
			GameRegistry.registerBlock(block, name);
			tinyStorageBlocks.add(block);
		} catch (Exception e) {
			TinyStorageLog.error(e);
		}
	}

}
