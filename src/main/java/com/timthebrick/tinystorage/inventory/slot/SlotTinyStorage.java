package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotTinyStorage extends Slot {

	/**
	 * 
	 * @param inv Inventory the slot is in, usually a TileEntity
	 * @param id The ID of the slot, must be unique!
	 * @param x The X coordinate of the left of the slot
	 * @param y The Y coordinate of the top of the slot
	 */
	public SlotTinyStorage(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
}
