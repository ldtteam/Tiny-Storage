package com.smithsmodding.tinystorage.common.inventory.slot;

import com.smithsmodding.tinystorage.util.common.StackHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

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
		if(StackHelper.isMatchingItem(testStack, filterStack, true, false)){
			return true;
		}
		if(StackHelper.hasOreDictEntry(testStack) && StackHelper.hasOreDictEntry(filterStack)){
			return StackHelper.isMatchingOreDict(testStack, filterStack);
		}
		return false;
	}

	public boolean containsInvalidStack() {
		if (filterStack == null && this.getStack() == null) {
			return false;
		} else if (filterStack == null && this.getStack() != null) {
			return true;
		} else if (filterStack != null && this.getStack() != null) {
			return !(isItemValid(this.getStack()));
		} else {
			return false;
		}
	}
}
