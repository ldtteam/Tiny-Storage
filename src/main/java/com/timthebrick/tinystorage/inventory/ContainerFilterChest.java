package com.timthebrick.tinystorage.inventory;

import net.minecraft.entity.player.InventoryPlayer;

import com.timthebrick.tinystorage.tileentity.TileEntityFilterChest;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyChest;

public class ContainerFilterChest extends ContainerTinyStorage {

	// Small Chest
	public static final int SMALL_CHEST_INVENTORY_ROWS = 1;
	public static final int SMALL_CHEST_INVENTORY_COLUMNS = 10;
	public static final int SMALL_INVENTORY_SIZE = SMALL_CHEST_INVENTORY_ROWS * SMALL_CHEST_INVENTORY_COLUMNS;
	// Medium Chest
	public static final int MEDIUM_CHEST_INVENTORY_ROWS = 2;
	public static final int MEDIUM_CHEST_INVENTORY_COLUMNS = 10;
	public static final int MEDIUM_INVENTORY_SIZE = MEDIUM_CHEST_INVENTORY_ROWS * MEDIUM_CHEST_INVENTORY_COLUMNS;
	// Large Chest
	public static final int LARGE_CHEST_INVENTORY_ROWS = 3;
	public static final int LARGE_CHEST_INVENTORY_COLUMNS = 10;
	public static final int LARGE_INVENTORY_SIZE = LARGE_CHEST_INVENTORY_ROWS * LARGE_CHEST_INVENTORY_COLUMNS;

	private TileEntityFilterChest tileEntity;
	private InventoryPlayer inventory;
	private int chestInventoryRows;
	private int chestInventoryColumns;

	public ContainerFilterChest(InventoryPlayer inventory, TileEntityFilterChest tileEntityFilterChest) {
		this.tileEntity = tileEntityFilterChest;
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
	}

}
