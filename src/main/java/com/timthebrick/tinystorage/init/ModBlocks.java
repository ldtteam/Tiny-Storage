package com.timthebrick.tinystorage.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.timthebrick.tinystorage.block.BlockTinyChest;
import com.timthebrick.tinystorage.item.ItemBlockTinyChest;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(References.MOD_ID)
public class ModBlocks {

	public static Block blockTinyChestStone = new BlockTinyChest(Material.rock, "Stone");
	public static Block blockTinyChestOakLog = new BlockTinyChest(Material.wood, "OakLog");
	public static Block blockTinyChestAcaciaLog = new BlockTinyChest(Material.wood, "AcaciaLog");
	public static Block blockTinyChestBirchLog = new BlockTinyChest(Material.wood, "BirchLog");
	public static Block blockTinyChestJungleLog = new BlockTinyChest(Material.wood, "JungleLog");
	public static Block blockTinyChestSpruceLog = new BlockTinyChest(Material.wood, "SpruceLog");

	public static void init() {
		GameRegistry.registerBlock(blockTinyChestStone, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "Stone");
		GameRegistry.registerBlock(blockTinyChestOakLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "OakLog");
		GameRegistry.registerBlock(blockTinyChestAcaciaLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "AcaciaLog");
		GameRegistry.registerBlock(blockTinyChestBirchLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "BirchLog");
		GameRegistry.registerBlock(blockTinyChestJungleLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "JungleLog");
		GameRegistry.registerBlock(blockTinyChestSpruceLog, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST + "SpruceLog");
	}

}
