package com.timthebrick.tinystorage.common.block.storage.chests;

import com.timthebrick.tinystorage.common.block.BlockTinyStorage;
import com.timthebrick.tinystorage.common.reference.ChestSize;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityTinyChest;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTinyChest extends BlockTinyStorage {

    private final ChestSize chestSize;

    public BlockTinyChest(Properties properties, ChestSize chestSize) {
        super(properties);
        this.chestSize = chestSize;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityTinyChest(this.chestSize);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
        }
    }

}
