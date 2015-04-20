package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class SlotSpecificInput extends SlotTinyStorage {

	Class<? extends Item> filterItem;

	public SlotSpecificInput(IInventory inv, int id, int x, int y, Class<? extends Item> filterItem) {
		super(inv, id, x, y);
		this.filterItem = filterItem;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem().getClass().isInstance(filterItem);
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
