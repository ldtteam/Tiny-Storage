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
public class ItemBlockDenseMaterial extends ItemBlock
{

    public ItemBlockDenseMaterial(Block p_i45328_1_) {
        super(p_i45328_1_);
    }


    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }
}
