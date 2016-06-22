package com.smithsmodding.tinystorage.common.tileentity;

import com.smithsmodding.smithscore.common.tileentity.TileEntitySmithsCore;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate3D;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.guimanager.GuiManagerTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.state.TileEntityTinyStorageState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tim on 22/06/2016.
 */
public class TileEntityTinyStorage extends TileEntitySmithsCore<TileEntityTinyStorageState, GuiManagerTinyStorage> implements IModularChest, ITickable {

    public TileEntityTinyStorage() {
        super(new TileEntityTinyStorageState(), new GuiManagerTinyStorage());
    }

    @Override
    public Coordinate3D getLocation() {
        return null;
    }

    @Override
    public LinkedHashMap<String, IModule> getInstalledModules() {
        return getState().getInstalledModules();
    }

    @Override
    public void installModule(IModule module) {
        if (!getState().getInstalledModules().containsKey(module.getUniqueID())
                && getModuleCount() + 1 <= getModuleCount() && module.canInstall(this)) {
            getState().getInstalledModules().put(module.getUniqueID(), module);
            module.onInstalled();
        }
    }

    @Override
    public int getModuleLimit() {
        return getState().getModuleLimit();
    }

    @Override
    public int getModuleCount() {
        return getState().getInstalledModules().size();
    }

    @Override
    public int getSizeInventory() {
        int invSize = 0;
        for (Map.Entry<String, IModule> moduleSet : getState().getInstalledModules().entrySet()) {
            if (moduleSet.getValue() instanceof IStorageModule) {
                invSize += ((IStorageModule) moduleSet.getValue()).getSizeInventory();
            }
        }
        return invSize;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.getStackInSlot(index);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.decrStackSize(index, count);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return null;
    }

    @Override
    public void clearInventory() {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                storageModule.clearInventory();
            }
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    storageModule.setInventorySlotContents(index, stack);
                }
                index -= storageModule.getSizeInventory();
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {
        int sum = 0;
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                sum += storageModule.getInventoryStackLimit();
            }
        }
        return sum;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        for (IModule module : getState().getInstalledModules().values()) {
            if (module instanceof IStorageModule) {
                IStorageModule storageModule = (IStorageModule) module;
                if (index < storageModule.getSizeInventory()) {
                    return storageModule.isItemValidForSlot(index, stack);
                }
                index -= storageModule.getSizeInventory();
            }
        }
        return false;
    }

    @Override
    public void update() {
        for (Map.Entry<String, IModule> moduleSet : getState().getInstalledModules().entrySet()) {
            moduleSet.getValue().onTileEntityUpdate(this);
        }
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
        return References.TE + "-" + getLocation().toString();
    }
}
