package com.smithsmodding.tinystorage.common.block;

import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Tim on 22/06/2016.
 */
public class BlockChestBase extends BlockContainer {

    public BlockChestBase(Material mat, String blockName) {
        super(mat);
        this.setUnlocalizedName(blockName);
        this.setHardness(2.5F);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setRegistryName(References.MOD_ID, blockName);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTinyStorage(null);
    }
}
