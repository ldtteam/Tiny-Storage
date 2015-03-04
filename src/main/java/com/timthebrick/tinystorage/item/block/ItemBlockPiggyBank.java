package com.timthebrick.tinystorage.item.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.timthebrick.tinystorage.block.BlockTinyChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockPiggyBank extends ItemBlock {

	public ItemBlockPiggyBank(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
		int metaData = itemStack.getItemDamage();
		if (metaData == 0) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:piggyBankPrefix.small"));
		} else if (metaData == 1) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:piggyBankPrefix.medium"));
		} else if (metaData == 2) {
			list.add(StatCollector.translateToLocal("tooltip.tinystorage:piggyBankPrefix.large"));
		}
	}

}
