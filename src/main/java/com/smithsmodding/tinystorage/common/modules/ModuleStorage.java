package com.smithsmodding.tinystorage.common.modules;

import com.smithsmodding.tinystorage.api.common.chest.IModularChest;
import com.smithsmodding.tinystorage.api.common.modules.IStorageModule;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

/**
 * Created by Tim on 23/06/2016.
 */
public class ModuleStorage implements IStorageModule {

    private final String uniqueId;
    private final int size;
    private ItemStack[] inventory;
    private IModularChest hostChest;

    public ModuleStorage(String uniqueId, int size) {
        this.uniqueId = uniqueId;
        this.size = size;
        this.inventory = new ItemStack[size];
    }

    @Override
    public int getSizeInventory() {
        return size;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public void clearInventory() {
        inventory = new ItemStack[size];
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        hostChest.markDirty();
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public String getUniqueID() {
        return uniqueId;
    }

    @Override
    public void onTileEntityUpdate(IModularChest tileEntityModularChest) {
    }

    @Override
    public void onInstalled(IModularChest hostChest) {
        this.hostChest = hostChest;
    }

    @Override
    public boolean canInstall(IModularChest tileEntityModularChest) {
        return !tileEntityModularChest.getInstalledModules().containsKey(getUniqueID());
    }

    @Override
    public NBTTagCompound writeToNBT() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
        return tagCompound;
    }

    @Override
    public void loadFromNBT(NBTTagCompound tagCompound) {
        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
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
}
