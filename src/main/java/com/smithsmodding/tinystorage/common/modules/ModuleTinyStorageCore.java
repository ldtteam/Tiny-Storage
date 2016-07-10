package com.smithsmodding.tinystorage.common.modules;

import com.smithsmodding.tinystorage.api.client.modules.IModelProvidingModule;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import com.smithsmodding.tinystorage.api.reference.ModTranslationKeys;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.registry.ModuleRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

/**
 * Author Orion (Created on: 26.06.2016)
 */
public final class ModuleTinyStorageCore implements IModelProvidingModule, IStorageModule {

    private IModularChest chest;

    @Override
    public String getRegisteringModId() {
        return References.MOD_ID;
    }

    @Override
    public String getUniqueID() {
        return References.Modules.ModuleNames.CORE;
    }

    @Override
    public String getDisplayText() {
        return I18n.format(ModTranslationKeys.Modules.CORE);
    }

    @Override
    public void onTileEntityUpdate(IModularChest tileEntityModularChest) {
    }

    @Override
    public void onInstalled(IModularChest tileEntityModularChest) {
        chest = tileEntityModularChest;
    }

    @Override
    public boolean canInstall(IModularChest tileEntityModularChest) {
        return true;
    }

    @Override
    public NBTTagCompound writeToNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void loadFromNBT(NBTTagCompound tag) {
    }

    @Override
    public int getSizeInventory() {
        return chest.getModuleLimit();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index >= chest.getInstalledModules().size() - 1) {
            return null;
        }
        return ModuleRegistry.getInstance().getStackForModule(chest.getModuleAtPosition(index));
    }

    @Override
    public void clearInventory() {
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index >= chest.getModuleLimit() - 1) {
            return;
        }
        if (stack != null) {
            IModule module = ModuleRegistry.getInstance().getModuleFromStack(stack);
            if (module != null && chest.getModuleAtPosition(index) != null) {
                if (module.getUniqueID().equals(chest.getModuleAtPosition(index).getUniqueID())) {
                    return;
                }
            }
            chest.installModule(module);
        } else {
            if (chest.getModuleAtPosition(index) != null) {
                chest.removeModule(chest.getModuleAtPosition(index));
            }
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public void markDirty() {
        chest.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        IModule module = ModuleRegistry.getInstance().getModuleFromStack(stack);
        return module != null;
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
}
