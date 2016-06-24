package com.smithsmodding.tinystorage.common.modules;

import com.smithsmodding.smithscore.util.common.ItemStackHelper;
import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.ICustomFilterModule;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by Tim on 24/06/2016.
 */
public class ModuleFilter implements ICustomFilterModule, IStorageModule {

    private String uniqueID;
    private int size;
    private ItemStack[] filterItems;
    private IModularChest hostChest;

    public ModuleFilter(String uniqueID, int size) {
        this.uniqueID = uniqueID;
        this.size = size;
        this.filterItems = new ItemStack[size];
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
    public void markDirty() {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
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
    public boolean allowItemStack(int inventoryStackIndex, ItemStack stack) {
        for (ItemStack filter : filterItems) {
            if (ItemStackHelper.isMatchingItem(stack, filter, true, true)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getUniqueID() {
        return uniqueID;
    }

    @Override
    public String getDisplayText() {
        return "Filter Component | Capacity: " + filterItems.length;
    }

    @Override
    public void onTileEntityUpdate(IModularChest tileEntityModularChest) {
    }

    @Override
    public void onInstalled(IModularChest tileEntityModularChest) {
    }

    @Override
    public boolean canInstall(IModularChest tileEntityModularChest) {
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT() {
        return null;
    }

    @Override
    public void loadFromNBT(NBTTagCompound tag) {
    }
}
