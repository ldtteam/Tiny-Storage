package com.timthebrick.tinystorage.inventory.slot;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.reference.References;
import com.timthebrick.tinystorage.util.StackHelper;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

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
			return StackHelper.isMatchingItem(testStack, filterStack, true, false);
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
