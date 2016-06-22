package com.smithsmodding.tinystorage.common.tileentity;

import com.smithsmodding.smithscore.client.gui.management.IGUIManager;
import com.smithsmodding.smithscore.common.tileentity.TileEntitySmithsCore;
import com.smithsmodding.smithscore.common.tileentity.state.ITileEntityState;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate3D;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class TileEntityTinyStorage extends TileEntitySmithsCore implements IModularChest, ITickable {

    protected TileEntityTinyStorage(ITileEntityState initialState, IGUIManager manager) {
        super(initialState, manager);
    }

    @Override
    public Coordinate3D getLocation() {
        return null;
    }

    @Override
    public LinkedHashMap<String, IModule> getInstalledModules() {
        return ((TileEntityTinyStorageState) getState()).getInstalledModules();
    }

    @Override
    public void installModule(IModule module) {
    }

    @Override
    public int getModuleLimit() {
        return 0;
    }

    @Override
    public int getModuleCount() {
        return ((TileEntityTinyStorageState) getState()).getInstalledModules().size();
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public void clearInventory() {
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public void update() {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public String getContainerID() {
        return null;
    }
}
