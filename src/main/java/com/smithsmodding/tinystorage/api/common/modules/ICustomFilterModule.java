package com.smithsmodding.tinystorage.api.common.modules;

import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 22/06/2016.
 */
public interface ICustomFilterModule extends IModule {

    boolean allowItemStack(int inventoryStackIndex, ItemStack stack);

}
