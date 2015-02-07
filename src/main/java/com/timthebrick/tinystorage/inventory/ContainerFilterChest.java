package com.timthebrick.tinystorage.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.timthebrick.tinystorage.inventory.slot.SlotTinyStorage;
import com.timthebrick.tinystorage.inventory.slot.SlotTinyStorageFilter;
import com.timthebrick.tinystorage.tileentity.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

public class ContainerFilterChest extends ContainerTinyStorage {

	// Small Chest
	public static final int SMALL_CHEST_INVENTORY_ROWS = 1;
	public static final int SMALL_CHEST_INVENTORY_COLUMNS = 8;
	public static final int SMALL_INVENTORY_SIZE = SMALL_CHEST_INVENTORY_ROWS * SMALL_CHEST_INVENTORY_COLUMNS;
	// Medium Chest
	public static final int MEDIUM_CHEST_INVENTORY_ROWS = 2;
	public static final int MEDIUM_CHEST_INVENTORY_COLUMNS = 8;
	public static final int MEDIUM_INVENTORY_SIZE = MEDIUM_CHEST_INVENTORY_ROWS * MEDIUM_CHEST_INVENTORY_COLUMNS;
	// Large Chest
	public static final int LARGE_CHEST_INVENTORY_ROWS = 3;
	public static final int LARGE_CHEST_INVENTORY_COLUMNS = 8;
	public static final int LARGE_INVENTORY_SIZE = LARGE_CHEST_INVENTORY_ROWS * LARGE_CHEST_INVENTORY_COLUMNS;

	private TileEntityFilterChest tileEntity;
	private InventoryPlayer inventory;
	private int chestInventoryRows;
	private int chestInventoryColumns;

	public ContainerFilterChest(InventoryPlayer inventoryPlayer, TileEntityFilterChest tileEntity) {
		this.tileEntity = tileEntity;
		tileEntity.openInventory();

		if (this.tileEntity.getState() == 0) {
			chestInventoryRows = SMALL_CHEST_INVENTORY_ROWS;
			chestInventoryColumns = SMALL_CHEST_INVENTORY_COLUMNS;
		} else if (this.tileEntity.getState() == 1) {
			chestInventoryRows = MEDIUM_CHEST_INVENTORY_ROWS;
			chestInventoryColumns = MEDIUM_CHEST_INVENTORY_COLUMNS;
		} else if (this.tileEntity.getState() == 2) {
			chestInventoryRows = LARGE_CHEST_INVENTORY_ROWS;
			chestInventoryColumns = LARGE_CHEST_INVENTORY_COLUMNS;
		}

		// Add chest slots to inventory

		if (this.tileEntity.getState() == 0) {
			this.addSlotToContainer(new SlotTinyStorageFilter(tileEntity, 0, 8, 20));
			for (int x = 1; x < SMALL_CHEST_INVENTORY_COLUMNS; x++) {
				this.addSlotToContainer(new SlotTinyStorage(tileEntity, x, 44 + (18 * (x - 1)), 20, tileEntity.getStackInSlot(0)));
			}
		} else if (this.tileEntity.getState() == 1) {

		} else if (this.tileEntity.getState() == 2) {

		}

		// Add player inventory to inventory
		for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
				if (this.tileEntity.getState() == 0) {
					this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 51 + inventoryRowIndex * 18));
				} else if (this.tileEntity.getState() == 1) {
					this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 69 + inventoryRowIndex * 18));
				} else if (this.tileEntity.getState() == 2) {
					this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 87 + inventoryRowIndex * 18));
				}
			}
		}

		// Add player hotbar to inventory
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
			if (this.tileEntity.getState() == 0) {
				this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 109));
			} else if (this.tileEntity.getState() == 1) {
				this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 127));
			} else if (this.tileEntity.getState() == 2) {
				this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 145));
			}
		}
	}
}
