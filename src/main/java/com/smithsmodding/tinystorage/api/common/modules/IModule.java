package com.smithsmodding.tinystorage.api.common.modules;

import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IModule {

    String getUniqueID();

    String getDisplayText();

    void onTileEntityUpdate(IModularChest tileEntityModularChest);

    void onInstalled(IModularChest tileEntityModularChest);

    boolean canInstall(IModularChest tileEntityModularChest);

    NBTTagCompound writeToNBT();

    void loadFromNBT(NBTTagCompound tag);
}
