package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotRestrictedInput extends SlotTinyStorage {
	
	public ItemStack filterStack;

	public SlotRestrictedInput(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	public void setFilterStack(ItemStack filterStack){
		this.filterStack = filterStack;
	}

	@Override
	public boolean isItemValid(ItemStack testStack) {
		if(filterStack != null){
			return (testStack.getItem() == filterStack.getItem());
		}else{
			return false;
		}
	}

}
