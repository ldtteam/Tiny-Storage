package com.smithsmodding.tinystorage.api.common.modules;

import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 23/06/2016.
 */
public interface IModuleProvider {

    IModule getModule(ItemStack stack);

    String getModuleID(ItemStack stack);

}
