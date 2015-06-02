package com.timthebrick.tinystorage.block;

import com.timthebrick.tinystorage.creativetab.TabTinyStorage;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockDenseMaterial extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] icon;

    public BlockDenseMaterial (Material mat) {
        super(mat);
        this.setHardness(2.5f);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setBlockName("blockDenseBlock");
    }

    @Override
    public void getSubBlocks (Item item, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
    }

    @Override
    public int damageDropped (int metaData) {
        return metaData;
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int j1 = p_149660_1_.getBlockMetadata(p_149660_2_, p_149660_3_, p_149660_4_);
        System.out.println("Placed: " + j1);

        return super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
    }

    @Override
    public IIcon getIcon (int side, int meta) {
        return icon[meta];
    }

    @Override
    public void registerBlockIcons (IIconRegister iconRegister) {
        icon = new IIcon[3];
        icon[0] = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":blockDenseA");
        icon[1] = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":blockDenseB");
        icon[2] = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":blockDenseC");
    }
}
