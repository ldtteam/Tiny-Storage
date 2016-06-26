package com.smithsmodding.tinystorage.api.common.modules;

import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IInteractableModule extends IModule {

    /**
     * Method that gets invoked when the chest is clicked
     *
     * @param modularChest The chest that was clicked
     * @param player       The player that clicked on the block
     * @param mouseButton  Which button got clicked
     */
    void onMouseClicked(IModularChest modularChest, EntityPlayer player, int mouseButton);

}
