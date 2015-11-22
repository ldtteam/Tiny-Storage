package com.smithsmodding.tinystorage.common.tileentity.implementations;

import com.smithsmodding.tinystorage.common.inventory.implementations.ContainerDraw;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.smithsmodding.tinystorage.util.common.math.ArrayHelper;
import com.smithsmodding.tinystorage.client.gui.widgets.settings.AccessMode;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.util.common.CommonSoundHelper;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityDraw extends TileEntityTinyStorage implements ISidedInventory {

    private int numPlayersUsing;
    private int ticksSinceSync;
    private ItemStack[] inventory;
    private int[] sides;
    private boolean playSoundEvent;
    public int rowOpened;

    public TileEntityDraw() {
        super();
        inventory = new ItemStack[ContainerDraw.INVENTORY_SIZE];
        sides = ArrayHelper.fillIntArray(0, ContainerDraw.INVENTORY_SIZE - 1, true);
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
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
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
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
    public String getInventoryName() {
        if (this.hasCustomName()) {
            return this.getCustomName();
        } else if (this.hasUniqueOwner()) {
            return Names.Containers.DRAW_LOCKED;
        } else {
            return Names.Containers.DRAW;
        }
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        ++numPlayersUsing;
        playSoundEvent = true;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public void closeInventory() {
        --numPlayersUsing;
        playSoundEvent = true;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (++ticksSinceSync % 20 * 4 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
        }

        double adjustedXCoord, adjustedZCoord;

        if (numPlayersUsing >= 0 && playSoundEvent) {
            CommonSoundHelper.playSoundAt(Minecraft.getMinecraft().thePlayer, "drawOpen", 1.5F, 0.5F, 3F);
            playSoundEvent = false;
        }

    }

    @Override
    public boolean receiveClientEvent(int eventID, int numUsingPlayers) {
        if (eventID == 1) {
            this.numPlayersUsing = numUsingPlayers;
            return true;
        } else {
            return super.receiveClientEvent(eventID, numUsingPlayers);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        readSyncedNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
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
    public void readSyncedNBT(NBTTagCompound tag) {
        super.readSyncedNBT(tag);
    }

    @Override
    public void writeSyncedNBT(NBTTagCompound tag) {
        super.writeSyncedNBT(tag);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncedNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readSyncedNBT(pkt.func_148857_g());
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return this.sides;
    }

    @Override
    public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
        if (this.accessMode == AccessMode.DISABLED) {
            return false;
        } else if (this.accessMode == AccessMode.INPUT_ONLY) {
            return this.isItemValidForSlot(slotID, stack);
        } else if (this.accessMode == AccessMode.OUTPUT_ONLY) {
            return false;
        } else if (this.accessMode == AccessMode.INPUT_OUTPUT) {
            return this.isItemValidForSlot(slotID, stack);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
        if (this.accessMode == AccessMode.DISABLED) {
            return false;
        } else if (this.accessMode == AccessMode.INPUT_ONLY) {
            return false;
        } else if (this.accessMode == AccessMode.OUTPUT_ONLY) {
            return true;
        } else if (this.accessMode == AccessMode.INPUT_OUTPUT) {
            return true;
        }
        return false;
    }
}
