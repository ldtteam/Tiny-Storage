package com.timthebrick.tinystorage.common.block.storage.chests;

import com.timthebrick.tinystorage.common.reference.Names;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.common.reference.RenderIDs;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityClayChestLarge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClayChestLarge extends BlockClayChest {

	public BlockClayChestLarge(boolean isLockable) {
		super(isLockable);
		if (!this.isLockable) {
			this.setBlockName(Names.UnlocalisedBlocks.CLAY_CHEST_LARGE);
		} else {
			this.setBlockName(Names.UnlocalisedBlocks.CLAY_CHEST_LARGE_LOCKED);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		return new TileEntityClayChestLarge();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		updateChestBounds(world.getBlockMetadata(x, y, z));
	}

	private void updateChestBounds(int meta) {
		setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
	}

	@Override
	public int getRenderType() {
		return RenderIDs.clayChestLarge;
	}

}
