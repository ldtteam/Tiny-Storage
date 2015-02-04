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

	public static Block blockTinyChestStone = new BlockTinyChest(Material.rock);

	public static void init() {
		GameRegistry.registerBlock(blockTinyChestStone, ItemBlockTinyChest.class, Names.Blocks.TINY_CHEST);
	}

}
