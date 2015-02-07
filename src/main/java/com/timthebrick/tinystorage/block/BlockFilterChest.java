package com.timthebrick.tinystorage.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFilterChest extends BlockContainer implements ITileEntityProvider {

	protected BlockFilterChest(Material mat, String textureName) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		return null;
	}

}
