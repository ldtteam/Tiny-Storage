package com.smithsmodding.tinystorage.api.common.modules;

import com.smithsmodding.smithscore.common.inventory.IItemStorage;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IStorageModule extends IModule, IItemStorage {

    default ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }
}
