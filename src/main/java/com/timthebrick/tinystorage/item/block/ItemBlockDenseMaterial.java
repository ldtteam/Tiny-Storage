package com.timthebrick.tinystorage.item.block;

import com.timthebrick.tinystorage.reference.Messages;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

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

    @Override
    public void addInformation (ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int metaData = itemStack.getItemDamage();
        if (metaData == 0) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_1));
        } else if (metaData == 1) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_2));
        } else if (metaData == 2) {
            list.add(StatCollector.translateToLocal(Messages.ItemTooltips.ITEM_TIER_3));
        }
    }

    public int getMetadata (int meta) {
        return meta;
    }
}
