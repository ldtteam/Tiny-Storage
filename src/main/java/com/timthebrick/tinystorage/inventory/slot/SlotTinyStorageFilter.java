package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotTinyStorageFilter extends Slot {
	
	public SlotTinyStorageFilter(IInventory inventory, int id, int xPos, int yPos) {
		super(inventory, id, xPos, yPos);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public void onSlotChange(ItemStack stackA, ItemStack stackB) {
		super.onSlotChange(stackA, stackB);
	}

	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
	}

}
