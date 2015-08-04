package com.timthebrick.tinystorage.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockTrashChest extends ItemBlock {

	public ItemBlockTrashChest(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}

}
