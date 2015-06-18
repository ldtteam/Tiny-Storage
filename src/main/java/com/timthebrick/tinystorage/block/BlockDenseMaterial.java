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
import java.util.Random;

public class BlockDenseMaterial extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon[] icon;

    public BlockDenseMaterial (Material mat) {
        super(mat);
        this.setHardness(2.5f);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setBlockName(Names.UnlocalisedBlocks.DENSE_BLOCK);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
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
    public void randomDisplayTick (World world, int x, int y, int z, Random random) {
        super.randomDisplayTick(world, x, y, z, random);
        if ((random.nextInt(2) == 0)) {
            world.spawnParticle("portal", x + random.nextFloat(), y + 1F, z + random.nextFloat(), 0.0D, 0.05D, 0.0D);
        }
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
