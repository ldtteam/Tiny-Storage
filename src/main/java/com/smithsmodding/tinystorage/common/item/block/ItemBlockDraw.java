package com.smithsmodding.tinystorage.common.item.block;

import com.smithsmodding.tinystorage.common.block.storage.BlockDraw;
import com.smithsmodding.tinystorage.common.reference.Messages;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockDraw extends ItemBlock {

	public ItemBlockDraw(Block block) {
		super(block);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		if (Block.getBlockFromItem(itemStack.getItem()) != Blocks.air && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockDraw) {
            BlockDraw block = (BlockDraw) Block.getBlockFromItem(itemStack.getItem());
			if(block.getIsLockable()){
				//noinspection unchecked
				list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_LOCKED));
			}
		}
	}

}
