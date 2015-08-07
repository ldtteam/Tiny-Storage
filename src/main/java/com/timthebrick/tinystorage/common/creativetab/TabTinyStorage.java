package com.timthebrick.tinystorage.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.common.init.ModBlocks;
import com.timthebrick.tinystorage.common.reference.References;

public class TabTinyStorage {

	public static final CreativeTabs creativeTab = new CreativeTabs(References.MOD_ID.toLowerCase()) {
		@Override
		public Item getTabIconItem () {
			return new ItemStack(ModBlocks.blockImpossibleChest).getItem();
		}
	};
}
