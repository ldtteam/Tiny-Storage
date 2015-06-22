package com.timthebrick.tinystorage.tileentity.implementations;

import com.timthebrick.tinystorage.inventory.implementations.ContainerBookCase;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.ArrayHelper;
import com.timthebrick.tinystorage.util.CommonSoundHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

import java.util.Arrays;

public class TileEntityBookCase extends TileEntityTinyStorage implements ISidedInventory {

    public int numPlayersUsing;
    private int ticksSinceSync;
    private ItemStack[] inventory;
    private int[] sides;

    public TileEntityBookCase () {
        super();
        inventory = new ItemStack[ContainerBookCase.INVENTORY_SIZE];
        sides = ArrayHelper.fillIntArray(0, ContainerBookCase.INVENTORY_SIZE - 1, true);
    }

    @Override
    public int getSizeInventory () {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot (int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize (int slotIndex, int decrementAmount) {
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
    public ItemStack getStackInSlotOnClosing (int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents (int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName () {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.BOOKCASE;
    }

    @Override
    public boolean hasCustomInventoryName () {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit () {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory () {
        ++numPlayersUsing;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public void closeInventory () {
        --numPlayersUsing;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public boolean isItemValidForSlot (int slotID, ItemStack stack) {
        if (stack.getItem() instanceof ItemBook || stack.getItem() instanceof ItemEditableBook || stack.getItem() instanceof ItemEnchantedBook || stack.getItem() instanceof ItemWritableBook) {
            return true;
        }
        return false;
    }

    @Override
    public void updateEntity () {
        super.updateEntity();
        if (++ticksSinceSync % 20 * 4 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            this.markDirty();
        }
    }

    @Override
    public boolean receiveClientEvent (int eventID, int numUsingPlayers) {
        if (eventID == 1) {
            this.numPlayersUsing = numUsingPlayers;
            return true;
        } else {
            return super.receiveClientEvent(eventID, numUsingPlayers);
        }
    }

    @Override
    public void readFromNBT (NBTTagCompound tagCompound) {
        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        super.readFromNBT(tagCompound);
        readSyncedNBT(tagCompound);
    }

    @Override
    public void writeToNBT (NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
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
        writeSyncedNBT(tagCompound);
    }

    @Override
    public void writeSyncedNBT (NBTTagCompound tagCompound) {
        super.writeSyncedNBT(tagCompound);
        int res = 0;
        for (int slot = 0; slot < getSizeInventory(); slot++) {
            if (getStackInSlot(slot) != null) {
                res |= (1 << slot);
            }
        }
        tagCompound.setInteger("SlotsFilled", res);
    }

    @Override
    public void readSyncedNBT (NBTTagCompound tagCompound) {
        super.readSyncedNBT(tagCompound);
        int slots = tagCompound.getInteger("SlotsFilled");
        for (int slot = 0; slot < getSizeInventory(); slot++) {
            if ((slots & (1 << slot)) != 0) {
                setInventorySlotContents(slot, new ItemStack(Blocks.stone));
            }else{
                setInventorySlotContents(slot, null);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket () {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncedNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket (NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readSyncedNBT(pkt.func_148857_g());
    }

    @Override
    public int[] getAccessibleSlotsFromSide (int side) {
        return this.sides;
    }

    @Override
    public boolean canInsertItem (int slotID, ItemStack stack, int blockSide) {
        return this.isItemValidForSlot(slotID, stack);
    }

    @Override
    public boolean canExtractItem (int slotID, ItemStack stack, int blockSide) {
        return true;
    }
}
