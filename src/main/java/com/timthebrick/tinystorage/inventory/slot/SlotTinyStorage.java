package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTinyStorage extends Slot {

	protected ItemStack filterStack;

	public SlotTinyStorage(IInventory inventory, int id, int xPos, int yPos, ItemStack filterStack) {
		super(inventory, id, xPos, yPos);
		this.filterStack = filterStack;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if (filterStack != null) {
			if (stack.getItem() == filterStack.getItem()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
