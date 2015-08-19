package com.timthebrick.tinystorage.common.inventory.slot;

import com.timthebrick.tinystorage.util.client.ResourceLocationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SlotDisabled extends SlotTinyStorage{

    public SlotDisabled (IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }

    @Override
    public boolean isItemValid (ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeStack (EntityPlayer player) {
        return false;
    }

    @Override
    public ResourceLocation getBackgroundIconTexture () {
        return ResourceLocationHelper.getResourceLocation(":textures/gui/slotDisabled.png");
    }
}
