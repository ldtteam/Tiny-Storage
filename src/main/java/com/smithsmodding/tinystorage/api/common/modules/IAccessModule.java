package com.smithsmodding.tinystorage.api.common.modules;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IAccessModule extends IModule {

    boolean isAccessAllowed(EntityPlayer player);

}
