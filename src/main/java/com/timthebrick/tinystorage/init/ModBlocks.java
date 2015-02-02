package com.timthebrick.tinystorage.init;

import net.minecraft.block.Block;

import com.timthebrick.tinystorage.block.BlockTinyChest;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block blockTinyChest = new BlockTinyChest();
	
	public static void init(){
		GameRegistry.registerBlock(blockTinyChest, "blockTinyChest");
	}

}
