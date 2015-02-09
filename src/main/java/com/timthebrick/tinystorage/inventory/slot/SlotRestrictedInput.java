package com.timthebrick.tinystorage.inventory.slot;

import com.timthebrick.tinystorage.reference.References;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SlotRestrictedInput extends SlotTinyStorage {

	public ItemStack filterStack;

	public SlotRestrictedInput(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	public void setFilterStack(ItemStack filterStack) {
		this.filterStack = filterStack;
	}

	@Override
	public boolean isItemValid(ItemStack testStack) {
		if (filterStack != null) {
			return (testStack.getItem() == filterStack.getItem());
		} else {
			return false;
		}
	}

	public boolean containsInvalidStack() {
		if (filterStack == null && this.getStack() == null) {
			return false;
		} else if (filterStack == null && this.getStack() != null) {
			return true;
		} else if (filterStack != null && this.getStack() != null) {
			return (filterStack.getItem() != this.getStack().getItem());
		} else {
			return false;
		}
	}
}
