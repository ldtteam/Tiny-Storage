package com.timthebrick.tinystorage.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

public class ContainerTinyChest extends ContainerTinyStorage {

	public static final int INVENTORY_ROWS = 1;
	public static final int INVENTORY_COLUMNS = 9;
	public static final int INVENTORY_SIZE = INVENTORY_ROWS * INVENTORY_COLUMNS;

	private TileEntityTinyChest tileEntity;
	private InventoryPlayer inventory;

	public ContainerTinyChest(InventoryPlayer inventoryPlayer, TileEntityTinyChest tileEntity) {
		this.tileEntity = tileEntity;
		tileEntity.openInventory();

		// Add the inventory slots to the container
		for (int y = 0; y < INVENTORY_ROWS; y++) {
			for (int x = 0; x < INVENTORY_COLUMNS; x++) {
				this.addSlotToContainer(new Slot(tileEntity, y + x * INVENTORY_ROWS, 8 + x * 18, 20 + y * 18));
			}
		}

		// Add the player's inventory to the container
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
			}
		}

		// Add the player's action slots to the container
		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 109));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
		tileEntity.closeInventory();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		ItemStack newItemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemStack = slot.getStack();
			newItemStack = itemStack.copy();
			if (slotIndex < INVENTORY_ROWS * INVENTORY_COLUMNS + 4) {
				if (!this.mergeItemStack(itemStack, INVENTORY_ROWS * INVENTORY_COLUMNS + 4, inventorySlots.size(), false)) { return null; }
			} else if (!this.mergeItemStack(itemStack, 4, INVENTORY_ROWS * INVENTORY_COLUMNS + 4, false)) { return null; }
			if (itemStack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}
		return newItemStack;
	}

}
