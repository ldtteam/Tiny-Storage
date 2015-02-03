package com.timthebrick.tinystorage.creativetab;

import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.reference.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabTinyStorage {
	
	public static final CreativeTabs creativeTab = new CreativeTabs(References.MOD_ID.toLowerCase()) {
		public Item getTabIconItem() {
			return (new ItemStack(ModBlocks.blockTinyChestStone, 1, 0).getItem());
		}
	};


}
