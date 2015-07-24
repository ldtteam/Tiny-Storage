package com.timthebrick.tinystorage.item.block;

import com.timthebrick.tinystorage.block.BlockImpossibleChest;
import com.timthebrick.tinystorage.block.BlockTinyChest;
import com.timthebrick.tinystorage.reference.Messages;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemBlockImpossibleChest extends ItemBlock {

    public ItemBlockImpossibleChest(Block block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
        list.add(StatCollector.translateToLocal(Messages.ItemTooltips.IMPOSSIBLE_CHEST));
        if (Block.getBlockFromItem(itemStack.getItem()) != Blocks.air && Block.getBlockFromItem(itemStack.getItem()) instanceof BlockImpossibleChest) {
            BlockImpossibleChest block = (BlockImpossibleChest) Block.getBlockFromItem(itemStack.getItem());
            if(block.getIsLockable()){
                list.add(StatCollector.translateToLocal(Messages.ItemTooltips.BLOCK_LOCKED));
            }
        }
    }
}
