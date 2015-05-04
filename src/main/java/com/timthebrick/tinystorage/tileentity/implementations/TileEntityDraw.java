package com.timthebrick.tinystorage.tileentity.implementations;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.timthebrick.tinystorage.inventory.implementations.ContainerDraw;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.CommonSoundHelper;

public class TileEntityDraw extends TileEntityTinyStorage implements ISidedInventory {

	public int numPlayersUsing;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	private int[] sides;
	private boolean playSoundEvent;

	public TileEntityDraw() {
		super();
		inventory = new ItemStack[ContainerDraw.INVENTORY_SIZE];
		sides = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
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
		return this.hasCustomName() ? this.getCustomName() : Names.Containers.DRAW;
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
			adjustedXCoord = xCoord + 0.5D;
			adjustedZCoord = zCoord + 0.5D;
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
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
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
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return this.sides;
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
		return this.isItemValidForSlot(slotID, stack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
		return true;
	}

}
