package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTinyStorage extends Slot {

	public SlotTinyStorage(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
}
