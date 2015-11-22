package com.smithsmodding.tinystorage.common.block;

import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerTinyStorage extends BlockContainer {

    protected BlockContainerTinyStorage(Material mat) {
        super(mat);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity != null && tileEntity instanceof TileEntityTinyStorage){
            ((TileEntityTinyStorage) tileEntity).onTileEntityDestroyed();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
}
