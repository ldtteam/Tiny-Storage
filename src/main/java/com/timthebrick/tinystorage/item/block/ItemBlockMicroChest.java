package com.timthebrick.tinystorage.item.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.timthebrick.tinystorage.block.BlockMicroChest;
import com.timthebrick.tinystorage.block.BlockTinyChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockMicroChest extends ItemBlock{
	
	public ItemBlockMicroChest(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		if (Block.getBlockFromItem(itemStack.getItem()) != Blocks.air && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockMicroChest) {
			BlockMicroChest block = (BlockMicroChest) Block.getBlockFromItem(itemStack.getItem());
			if(block.getIsLockable()){
				list.add(StatCollector.translateToLocal("tooltip.tinystorage:microChestPrefix.locked"));
			}
		}
	}

}
