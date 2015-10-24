package com.smithsmodding.tinystorage.common.inventory.implementations;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.smithsmodding.tinystorage.common.inventory.ContainerTinyStorage;
import com.smithsmodding.tinystorage.common.inventory.slot.SlotSpecificInput;
import com.smithsmodding.tinystorage.common.inventory.slot.SlotTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityPeacefulChest;

public class ContainerPeacefulChest extends ContainerTinyStorage {

	// Small Chest
	private static final int SMALL_CHEST_INVENTORY_ROWS = 1;
	private static final int SMALL_CHEST_INVENTORY_COLUMNS = 7;
	public static final int SMALL_INVENTORY_SIZE = 1 + SMALL_CHEST_INVENTORY_ROWS * SMALL_CHEST_INVENTORY_COLUMNS;
	// Medium Chest
	private static final int MEDIUM_CHEST_INVENTORY_ROWS = 2;
	private static final int MEDIUM_CHEST_INVENTORY_COLUMNS = 7;
	public static final int MEDIUM_INVENTORY_SIZE = 1 + MEDIUM_CHEST_INVENTORY_ROWS * MEDIUM_CHEST_INVENTORY_COLUMNS;
	// Large Chest
	private static final int LARGE_CHEST_INVENTORY_ROWS = 3;
	private static final int LARGE_CHEST_INVENTORY_COLUMNS = 7;
	public static final int LARGE_INVENTORY_SIZE = 1 + LARGE_CHEST_INVENTORY_ROWS * LARGE_CHEST_INVENTORY_COLUMNS;

	private TileEntityPeacefulChest tileEntity;
	private InventoryPlayer inventory;
	private int chestInventoryRows;
	private int chestInventoryColumns;

	public ContainerPeacefulChest(InventoryPlayer inventoryPlayer, TileEntityPeacefulChest tileEntity) {
		this.tileEntity = tileEntity;
		this.tileEntityTinyStorage = tileEntity;
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

		// Add the sword slot to the container
		this.addSlotToContainer(new SlotSpecificInput(tileEntity, 0, 8, 20, ItemSword.class));

		// Add the inventory slots to the container
		for (int chestRowIndex = 0; chestRowIndex < chestInventoryRows; chestRowIndex++) {
			for (int chestColumnIndex = 0; chestColumnIndex < chestInventoryColumns; chestColumnIndex++) {
				this.addSlotToContainer(new SlotTinyStorage(tileEntity, 1 + (chestColumnIndex + chestRowIndex * chestInventoryColumns), 44 + chestColumnIndex * 18, 20 + chestRowIndex * 18));

			}
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
				if (!this.mergeItemStack(itemStack, 1 + chestInventoryRows * chestInventoryColumns, inventorySlots.size(), false)) {
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
