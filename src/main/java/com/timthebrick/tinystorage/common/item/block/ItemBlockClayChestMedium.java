package com.timthebrick.tinystorage.common.item.block;

import java.util.List;

import com.timthebrick.tinystorage.common.reference.Messages;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import com.timthebrick.tinystorage.common.block.BlockClayChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockClayChestMedium extends ItemBlock {
	
	protected static final String[] textureNames = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White" };

	public ItemBlockClayChestMedium(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + textureNames[i];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_MEDIUM));
		if (Block.getBlockFromItem(itemStack.getItem()) != Blocks.air && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockClayChest) {
			BlockClayChest block = (BlockClayChest) Block.getBlockFromItem(itemStack.getItem());
			if (block.getIsLockable()) {
				list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_LOCKED));
			}
		}
	}

}
