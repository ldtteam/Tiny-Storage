package com.timthebrick.tinystorage.common.item.block;

import java.util.List;

import com.timthebrick.tinystorage.common.reference.Messages;
import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import com.timthebrick.tinystorage.common.block.storage.chests.BlockClayChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockClayChestSmall extends ItemBlock {

	public ItemBlockClayChestSmall(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + References.dyes[i];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		//noinspection unchecked
		list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_SMALL));
		if (Block.getBlockFromItem(itemStack.getItem()) != Blocks.air && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockClayChest) {
			BlockClayChest block = (BlockClayChest) Block.getBlockFromItem(itemStack.getItem());
			if (block.getIsLockable()) {
				//noinspection unchecked
				list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_LOCKED));
			}
		}
	}

}
