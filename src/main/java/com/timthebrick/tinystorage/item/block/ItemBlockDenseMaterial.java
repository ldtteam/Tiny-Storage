package com.timthebrick.tinystorage.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by Orion
 * Created on 02.06.2015
 * 16:17
 * <p/>
 * Copyrighted according to Project specific license
 */
public class ItemBlockDenseMaterial extends ItemBlock {

    public ItemBlockDenseMaterial (Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public int getMetadata (int meta) {
        return meta;
    }
}
