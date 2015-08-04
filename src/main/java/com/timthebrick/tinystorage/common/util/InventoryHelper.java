package com.timthebrick.tinystorage.common.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

	public static ItemStack invInsert(IInventory inventory, ItemStack itemStack, int side) {
		if ((itemStack != null) && (itemStack.stackSize > 0)) {
			boolean isNonSidedInventory = !(inventory instanceof ISidedInventory);
			int nextEmptySlot = -1;
			int filter = -1;
			int maxStackSize = Math.min(itemStack.getMaxStackSize(), inventory.getInventoryStackLimit());
			filter = maxStackSize;
			boolean flag = false;
			for (int slotID : InventoryHelper.getValidSlotsForSide(inventory, side)) {
				ItemStack dest = inventory.getStackInSlot(slotID);
				if (dest == null) {
					if ((nextEmptySlot == -1) && (inventory.isItemValidForSlot(slotID, itemStack)) && ((isNonSidedInventory) || (((ISidedInventory) inventory).canInsertItem(slotID, itemStack, side)))) {
						nextEmptySlot = slotID;
					}
				} else if ((StackHelper.canStacksMerge(itemStack, dest)) && (inventory.isItemValidForSlot(slotID, itemStack)) && ((isNonSidedInventory) || (((ISidedInventory) inventory).canInsertItem(slotID, itemStack, side))) && (maxStackSize - dest.stackSize > 0) && (filter > 0)) {
					int allowedStackSize = Math.min(Math.min(itemStack.stackSize, maxStackSize - dest.stackSize), filter);
					if (allowedStackSize > 0) {
						dest.stackSize += allowedStackSize;
						itemStack.stackSize -= allowedStackSize;
						filter -= allowedStackSize;
						flag = true;
						if (itemStack.stackSize <= 0) {
							itemStack = null;
						} else {
							if (filter <= 0) {
								break;
							}
						}
					}
				}
			}
			if ((filter > 0) && (itemStack != null) && (nextEmptySlot != -1) && (inventory.isItemValidForSlot(nextEmptySlot, itemStack)) && ((isNonSidedInventory) || (((ISidedInventory) inventory).canInsertItem(nextEmptySlot, itemStack, side)))) {
				if (filter < itemStack.stackSize) {
					inventory.setInventorySlotContents(nextEmptySlot, itemStack.splitStack(filter));
				} else {
					inventory.setInventorySlotContents(nextEmptySlot, itemStack);
					itemStack = null;
				}
				flag = true;
			}
			if (flag) {
				inventory.markDirty();
			}
		}
		return itemStack;
	}

	public static int[] getValidSlotsForSide(IInventory inv, int side) {
		if ((inv instanceof ISidedInventory)) {
			return ((ISidedInventory) inv).getAccessibleSlotsFromSide(side);
		}
		if (inv != null) {
			int size = inv.getSizeInventory();
			int[] validSlots = new int[size];
			for (int i = 0; i < size; i++) {
				validSlots[i] = i;
			}
			return validSlots;
		}
		return new int[0];
	}

}
