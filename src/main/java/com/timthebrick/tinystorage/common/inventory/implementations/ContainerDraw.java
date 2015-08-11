package com.timthebrick.tinystorage.common.inventory.implementations;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.common.inventory.ContainerTinyStorage;
import com.timthebrick.tinystorage.common.inventory.slot.SlotTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityDraw;

public class ContainerDraw extends ContainerTinyStorage {

	public static final int INVENTORY_ROWS = 3;
	public static final int INVENTORY_COLUMNS = 3;
	public static final int INVENTORY_SIZE = INVENTORY_ROWS * INVENTORY_COLUMNS;

	private TileEntityDraw tileEntity;
	private InventoryPlayer inventory;
	private int chestInventoryRows;
	private int chestInventoryColumns;

	public ContainerDraw(InventoryPlayer inventoryPlayer, TileEntityDraw tileEntity) {
		this.tileEntity = tileEntity;
		tileEntity.openInventory();

		chestInventoryRows = INVENTORY_ROWS;
		chestInventoryColumns = INVENTORY_COLUMNS;

		// Add slots to inventory
		if(tileEntity.rowOpened == 0) {
			for (int i = 0; i < 3; i++) {
				this.addSlotToContainer(new SlotTinyStorage(tileEntity, i, 62 + (18 * i), 12));
			}
		}else if(tileEntity.rowOpened == 1) {
			for (int i = 0; i < 3; i++) {
				this.addSlotToContainer(new SlotTinyStorage(tileEntity, 3 + i, 62 + (18 * i), 34));
			}
		}else if(tileEntity.rowOpened == 2) {
			for (int i = 0; i < 3; i++) {
				this.addSlotToContainer(new SlotTinyStorage(tileEntity, 6 + i, 62 + (18 * i), 56));
			}
		}

		// Add player inventory to inventory
		for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
				this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 87 + inventoryRowIndex * 18));
			}
		}

		// Add player hotbar to inventory
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
			this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 145));
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

			if (slotIndex < chestInventoryRows * chestInventoryColumns) {
				if (!this.mergeItemStack(itemStack, chestInventoryRows * chestInventoryColumns, inventorySlots.size(), false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemStack, 0, chestInventoryRows * chestInventoryColumns, false)) {
				return null;
			}

			if (itemStack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}

		return newItemStack;
	}

}
