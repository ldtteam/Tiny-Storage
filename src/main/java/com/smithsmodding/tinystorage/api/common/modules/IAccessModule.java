package com.smithsmodding.tinystorage.api.common.modules;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IAccessModule extends IModule {

    /**
     * Method to check if the given player is allowed to access the host chest
     *
     * @param player The player trying to access the chest
     * @return Whether the player is allowed access or not
     */
    boolean isAccessAllowed(EntityPlayer player);

}
