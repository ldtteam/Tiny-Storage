package com.smithsmodding.tinystorage.common.tileentity;

import com.smithsmodding.smithscore.common.tileentity.TileEntitySmithsCore;
import com.smithsmodding.smithscore.common.tileentity.state.ITileEntityState;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import net.minecraft.nbt.NBTBase;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class TileEntityTinyStorageState implements ITileEntityState {

    private LinkedHashMap<String, IModule> installedModules;

    @Override
    public void onStateCreated(TileEntitySmithsCore tileEntitySmithsCore) {
        installedModules = new LinkedHashMap<>();
    }

    @Override
    public void onStateUpdated() {

    }

    @Override
    public void onStateDestroyed() {

    }

    @Override
    public boolean requiresNBTStorage() {
        return false;
    }

    @Override
    public void readFromNBTTagCompound(NBTBase stateData) {

    }

    @Override
    public NBTBase writeToNBTTagCompound() {
        return null;
    }

    @Override
    public boolean requiresSynchronization() {
        return false;
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
}
