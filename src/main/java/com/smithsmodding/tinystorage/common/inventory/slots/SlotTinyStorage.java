package com.smithsmodding.tinystorage.common.inventory.slots;

import com.smithsmodding.smithscore.common.inventory.IItemStorage;
import com.smithsmodding.smithscore.common.inventory.slot.SlotSmithsCore;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by Tim on 07/07/2016.
 */
public class SlotTinyStorage extends SlotSmithsCore {

    public SlotTinyStorage(IItemStorage inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        return inventory.isItemValidForSlot(getSlotIndex(), stack);
    }
}
