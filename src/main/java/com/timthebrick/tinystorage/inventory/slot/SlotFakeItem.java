package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class SlotFakeItem extends SlotTinyStorage implements IFakeItemSlot{

	public SlotFakeItem(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	@Override
	public boolean canAdjust() {
		return true;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}

}
