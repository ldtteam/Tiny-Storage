package com.smithsmodding.tinystorage.common.tileentity.state;

import com.smithsmodding.smithscore.common.tileentity.TileEntitySmithsCore;
import com.smithsmodding.smithscore.common.tileentity.state.ITileEntityState;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class TileEntityTinyStorageState implements ITileEntityState {

    private LinkedHashMap<String, IModule> installedModules;
    private int moduleLimit;

    @Override
    public void onStateCreated(TileEntitySmithsCore tileEntitySmithsCore) {
        installedModules = new LinkedHashMap<>();
        moduleLimit = 0;
    }

    @Override
    public void onStateUpdated() {
    }

    @Override
    public void onStateDestroyed() {
    }

    @Override
    public boolean requiresNBTStorage() {
        return true;
    }

    @Override
    public void readFromNBTTagCompound(NBTBase stateData) {

    }

    @Override
    public NBTBase writeToNBTTagCompound() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setInteger("moduleLimit", moduleLimit);
        return tagCompound;
    }

    @Override
    public boolean requiresSynchronization() {
        return true;
    }

    @Override
    public void readFromSynchronizationCompound(NBTBase stateData) {

    }

    @Override
    public NBTBase writeToSynchronizationCompound() {
        return null;
    }

    public LinkedHashMap<String, IModule> getInstalledModules() {
        return installedModules;
    }

    public int getModuleLimit() {
        return moduleLimit;
    }
}
