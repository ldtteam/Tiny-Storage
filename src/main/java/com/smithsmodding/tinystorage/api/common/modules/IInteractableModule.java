package com.smithsmodding.tinystorage.api.common.modules;

import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IInteractableModule extends IModule {

    void onMouseClicked(IModularChest modularChest, EntityPlayer player, int mouseButton);

}
