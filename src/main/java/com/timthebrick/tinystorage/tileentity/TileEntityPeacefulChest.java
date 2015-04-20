package com.timthebrick.tinystorage.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.inventory.ContainerPeacefulChest;
import com.timthebrick.tinystorage.reference.Names;

public class TileEntityPeacefulChest extends TileEntityTinyStorage implements ISidedInventory {

	private ItemStack[] inventory;
	private int[] inventorySlots;
	private int swordSlot;

	public TileEntityPeacefulChest(int metaData) {
		super();
		swordSlot = 0;
		this.state = (byte) metaData;
		if (metaData == 0) {
			inventory = new ItemStack[ContainerPeacefulChest.SMALL_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		} else if (metaData == 1) {
			inventory = new ItemStack[ContainerPeacefulChest.MEDIUM_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		} else if (metaData == 2) {
			inventory = new ItemStack[ContainerPeacefulChest.LARGE_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
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
			return Names.Containers.PEACEFUL_CHEST_LOCKED;
		} else {
			return Names.Containers.PEACEFUL_CHEST;
		}
	}

	

}
