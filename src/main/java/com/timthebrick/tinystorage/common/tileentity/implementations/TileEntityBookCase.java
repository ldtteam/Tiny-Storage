package com.timthebrick.tinystorage.common.tileentity.implementations;

import com.timthebrick.tinystorage.common.inventory.implementations.ContainerBookCase;
import com.timthebrick.tinystorage.common.reference.Colours;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.math.ArrayHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

import java.util.Random;

public class TileEntityBookCase extends TileEntityTinyStorage implements ISidedInventory {

    private int numPlayersUsing;
    private int ticksSinceSync;
    private ItemStack[] inventory;
    private int[] sides;
    public int slotsFilled;
    public int[] bookColours = new int[ContainerBookCase.INVENTORY_SIZE];
    public float[] bookMultiply = new float[ContainerBookCase.INVENTORY_SIZE];
    private Random random;

    public TileEntityBookCase () {
        super();
        inventory = new ItemStack[ContainerBookCase.INVENTORY_SIZE];
        sides = ArrayHelper.fillIntArray(0, ContainerBookCase.INVENTORY_SIZE - 1, true);
        random = new Random();
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
        return stack.getItem() instanceof ItemBook || stack.getItem() instanceof ItemEditableBook || stack.getItem() instanceof ItemEnchantedBook || stack.getItem() instanceof ItemWritableBook;
    }

    @Override
    public void updateEntity () {
        super.updateEntity();
        if (++ticksSinceSync % 20 * 2 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
        }
        if (!worldObj.isRemote) {
            slotsFilled = 0;
            for (int slot = 0; slot < getSizeInventory(); slot++) {
                if (getStackInSlot(slot) != null) {
                    slotsFilled |= (1 << slot);
                    if (Colours.itemColourMap.containsKey(getStackInSlot(slot).getItem())) {
                        bookColours[slot] = Colours.itemColourMap.get(getStackInSlot(slot).getItem()).getColour();
                        if(bookMultiply[slot] == 0){
                            bookMultiply[slot] = random.nextFloat();
                        }
                    }
                } else {
                    bookColours[slot] = 0;
                    bookMultiply[slot] = 0;
                }
            }
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
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
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
        NBTTagList colourMultiply = new NBTTagList();
        for(int i = 0; i < ContainerBookCase.INVENTORY_SIZE; i++){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("Slot", i);
            tag.setFloat("Multi", bookMultiply[i]);
            colourMultiply.appendTag(tag);
        }
        tagCompound.setTag("ColourMultiplier", colourMultiply);
        tagCompound.setInteger("SlotsFilled", slotsFilled);
        tagCompound.setIntArray("SlotColours", bookColours);
    }

    @Override
    public void readSyncedNBT (NBTTagCompound tagCompound) {
        super.readSyncedNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("ColourMultiplier", 10);
        for(int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            int slot = tag.getInteger("Slot");
            if(slot > 0 && slot < ContainerBookCase.INVENTORY_SIZE){
                bookMultiply[i] = tag.getFloat("Multi");
            }
        }
        slotsFilled = tagCompound.getInteger("SlotsFilled");
        bookColours = tagCompound.getIntArray("SlotColours");
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
