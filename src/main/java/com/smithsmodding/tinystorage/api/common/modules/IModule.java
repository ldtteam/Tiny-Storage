package com.smithsmodding.tinystorage.api.common.modules;

import com.smithsmodding.tinystorage.api.common.chest.IModularChest;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IModule {

    String getUniqueID();

    void onTileEntityUpdate(IModularChest tileEntityModularChest);

    void onInstalled();

    boolean canInstall(IModularChest tileEntityModularChest);
}
