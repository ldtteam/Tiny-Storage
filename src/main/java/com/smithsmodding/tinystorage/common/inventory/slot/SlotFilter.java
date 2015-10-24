package com.smithsmodding.tinystorage.common.inventory.slot;

import net.minecraft.inventory.IInventory;

public class SlotFilter extends SlotFakeItem{

	public SlotFilter(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
