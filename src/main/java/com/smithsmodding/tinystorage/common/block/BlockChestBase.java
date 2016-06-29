package com.smithsmodding.tinystorage.common.block;

import com.smithsmodding.tinystorage.api.client.modules.IBlockStateDependingModelProvidingModule;
import com.smithsmodding.tinystorage.api.client.modules.IModelProvidingModule;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.properties.PropertyInstalledModules;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import com.smithsmodding.tinystorage.common.registry.ModuleRegistry;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Tim on 22/06/2016.
 */
public class BlockChestBase extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyInstalledModules INSTALLED_MODULES = new PropertyInstalledModules();

    private final ExtendedBlockState extendedState;

    public BlockChestBase() {
        super(Material.WOOD);
        this.setUnlocalizedName(References.Blocks.BLOCKCHESTBASE);
        this.setHardness(2.5F);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setRegistryName(References.MOD_ID.toLowerCase(), References.Blocks.BLOCKCHESTBASE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

        HashSet<IUnlistedProperty> requiredUnlistedProperties = new HashSet<>();
        requiredUnlistedProperties.add(INSTALLED_MODULES);

        for (IModule module : ModuleRegistry.getInstance().getAllBuildableModules()) {
            if (!(module instanceof IBlockStateDependingModelProvidingModule))
                continue;

            IBlockStateDependingModelProvidingModule blockstateModule = (IBlockStateDependingModelProvidingModule) module;

            requiredUnlistedProperties.addAll(blockstateModule.getRequiredProperties());
        }

        extendedState = new ExtendedBlockState(this, new IProperty[]{FACING}, requiredUnlistedProperties.toArray(new IUnlistedProperty[requiredUnlistedProperties.size()]));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTinyStorage();
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    protected BlockStateContainer createBlockState() {
        return new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{INSTALLED_MODULES});
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity entity = world.getTileEntity(pos);

        if (!(entity instanceof TileEntityTinyStorage))
            return state;

        TileEntityTinyStorage chestTe = (TileEntityTinyStorage) entity;
        HashSet<IModule> installedModules = new HashSet<>(chestTe.getInstalledModules().values());
        Iterator<IModule> moduleIterator = installedModules.iterator();

        state = ((IExtendedBlockState) extendedState.getBaseState().withProperty(FACING, state.getValue(FACING))).withProperty(INSTALLED_MODULES, installedModules);

        while (moduleIterator.hasNext()) {
            IModule module = moduleIterator.next();

            if (!(module instanceof IModelProvidingModule)) {
                moduleIterator.remove();
                continue;
            }

            if (module instanceof IBlockStateDependingModelProvidingModule) {
                IBlockStateDependingModelProvidingModule blockstateModule = (IBlockStateDependingModelProvidingModule) module;

                if (!blockstateModule.shouldRender(state, world, pos)) {
                    moduleIterator.remove();
                    continue;
                }

                state = blockstateModule.onGetExtendedState(state, world, pos);
            }
        }

        return state;
    }
}
