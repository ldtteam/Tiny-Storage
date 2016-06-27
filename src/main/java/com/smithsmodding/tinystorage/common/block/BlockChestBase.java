package com.smithsmodding.tinystorage.common.block;

import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Tim on 22/06/2016.
 */
public class BlockChestBase extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockChestBase() {
        super(Material.WOOD);
        this.setUnlocalizedName(References.Blocks.BLOCKCHESTBASE);
        this.setHardness(2.5F);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setRegistryName(References.MOD_ID.toLowerCase(), References.Blocks.BLOCKCHESTBASE);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTinyStorage();
    }
}
