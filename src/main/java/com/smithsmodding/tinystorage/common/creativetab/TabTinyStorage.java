package com.smithsmodding.tinystorage.common.creativetab;

import com.smithsmodding.tinystorage.common.init.ModBlocks;
import com.smithsmodding.tinystorage.common.reference.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabTinyStorage {

	public static final CreativeTabs creativeTab = new CreativeTabs(References.MOD_ID.toLowerCase()) {
		@Override
		public Item getTabIconItem () {
			return new ItemStack(ModBlocks.blockImpossibleChest).getItem();
		}
	};
}
