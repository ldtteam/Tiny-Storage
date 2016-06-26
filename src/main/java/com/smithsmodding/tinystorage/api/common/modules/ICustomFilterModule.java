package com.smithsmodding.tinystorage.api.common.modules;

import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 22/06/2016.
 */
public interface ICustomFilterModule extends IModule {

    /**
     * Method to check if the given ItemStack is allowed in the inventory at the specified slot
     *
     * @param inventoryStackIndex The index being inserted into
     * @param stack               The ItemStack that is trying to be inserted
     * @return Whether the ItemStack is allowed in the slot
     */
    boolean allowItemStack(int inventoryStackIndex, ItemStack stack);

}
