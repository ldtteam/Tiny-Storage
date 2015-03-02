package com.timthebrick.tinystorage.block;

import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityPiggyBankSmall;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChestSmall;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPiggyBank extends BlockContainer implements ITileEntityProvider {
	
	public BlockPiggyBank(Material mat) {
		super(mat);
		this.setBlockName("blockPiggyBank");
		this.setCreativeTab(TabTinyStorage.creativeTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		if (metaData == 0) {
			return new TileEntityPiggyBankSmall();
		} else if (metaData == 1) {
			return new TileEntityPiggyBankMedium();
		} else if (metaData == 2) {
			return new TileEntityPiggyBankLarge();
		}
		return null;
	}

}
