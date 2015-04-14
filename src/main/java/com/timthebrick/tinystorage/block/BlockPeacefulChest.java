package com.timthebrick.tinystorage.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.tileentity.TileEntityPeacefulChestLarge;
import com.timthebrick.tinystorage.tileentity.TileEntityPeacefulChestMedium;
import com.timthebrick.tinystorage.tileentity.TileEntityPeacefulChestSmall;

public class BlockPeacefulChest extends BlockContainer implements ITileEntityProvider {

	public BlockPeacefulChest(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		if (metaData == 0) {
			return new TileEntityPeacefulChestSmall();
		} else if (metaData == 1) {
			return new TileEntityPeacefulChestMedium();
		} else if (metaData == 2) {
			return new TileEntityPeacefulChestLarge();
		}
		return null;
	}

}
