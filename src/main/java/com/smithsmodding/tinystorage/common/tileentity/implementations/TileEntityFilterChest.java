package com.smithsmodding.tinystorage.common.tileentity.implementations;

import com.smithsmodding.tinystorage.client.gui.widgets.settings.AccessMode;
import com.smithsmodding.tinystorage.common.inventory.implementations.ContainerFilterChest;
import com.smithsmodding.tinystorage.common.reference.Names;
import com.smithsmodding.tinystorage.common.reference.Sounds;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.smithsmodding.tinystorage.util.common.StackHelper;
import com.smithsmodding.tinystorage.util.common.math.ArrayHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityFilterChest extends TileEntityTinyStorage implements ISidedInventory {

    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private ItemStack[] inventory;
    private int[] sides;

    public TileEntityFilterChest(int metaData) {
        super();
        this.state = (byte) metaData;
        if (metaData == 0) {
            inventory = new ItemStack[ContainerFilterChest.SMALL_INVENTORY_SIZE];
            sides = ArrayHelper.fillIntArray(1, 9, true);
        } else if (metaData == 1) {
            inventory = new ItemStack[ContainerFilterChest.MEDIUM_INVENTORY_SIZE];
            sides = ArrayHelper.fillIntArray(2, 15, true);
        } else if (metaData == 2) {
            inventory = new ItemStack[ContainerFilterChest.LARGE_INVENTORY_SIZE];
            sides = ArrayHelper.fillIntArray(3, 23, true);
        }
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
            return Names.Containers.FILTER_CHEST_LOCKED;
        } else {
            return Names.Containers.FILTER_CHEST;
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
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public void closeInventory() {
        --numPlayersUsing;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack stack) {
        if (this.getState() == 0) {
            if (slotID == 0) {
                return false;
            } else {
                return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
            }
        } else if (this.getState() == 1) {
            if (slotID == 0 || slotID == 1) {
                return false;
            } else {
                if (slotID > 1 && slotID <= 8) {
                    return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
                } else {
                    return StackHelper.isMatchingItem(stack, getStackInSlot(1), true, true);
                }
            }
        } else if (this.getState() == 2) {
            if (slotID == 0 || slotID == 1 || slotID == 2) {
                return false;
            } else {
                if (slotID > 2 && slotID <= 9) {
                    return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
                } else if (slotID > 9 && slotID <= 16) {
                    return StackHelper.isMatchingItem(stack, getStackInSlot(1), true, true);
                } else {
                    return StackHelper.isMatchingItem(stack, getStackInSlot(2), true, true);
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (++ticksSinceSync % 20 * 4 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
        }

        prevLidAngle = lidAngle;
        float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (numPlayersUsing > 0 && lidAngle == 0.0F) {
            adjustedXCoord = xCoord + 0.5D;
            adjustedZCoord = zCoord + 0.5D;
            worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_OPEN, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
            float var8 = lidAngle;

            if (numPlayersUsing > 0) {
                lidAngle += angleIncrement;
            } else {
                lidAngle -= angleIncrement;
            }

            if (lidAngle > 1.0F) {
                lidAngle = 1.0F;
            }

            if (lidAngle < 0.5F && var8 >= 0.5F) {
                adjustedXCoord = xCoord + 0.5D;
                adjustedZCoord = zCoord + 0.5D;
            }

            if (lidAngle < 0.0F) {
                lidAngle = 0.0F;
            }
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
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
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
