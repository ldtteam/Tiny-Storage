package com.smithsmodding.tinystorage.common.block.material;

import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.common.reference.References;
import com.smithsmodding.tinystorage.common.creativetab.TabTinyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockBlueLog extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public BlockBlueLog() {
        super(Material.wood);
        this.setCreativeTab(TabTinyStorage.creativeTab);
        this.setBlockName(Names.UnlocalisedBlocks.BLUE_LOG);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icon = iconRegister.registerIcon(References.MOD_ID.toLowerCase() + ":blockBlueLog");
    }

}
