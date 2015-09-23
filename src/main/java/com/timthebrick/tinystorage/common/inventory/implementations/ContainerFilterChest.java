package com.timthebrick.tinystorage.common.inventory.implementations;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.timthebrick.tinystorage.common.inventory.ContainerTinyStorage;
import com.timthebrick.tinystorage.common.inventory.slot.IFakeItemSlot;
import com.timthebrick.tinystorage.common.inventory.slot.SlotFilter;
import com.timthebrick.tinystorage.common.inventory.slot.SlotRestrictedInput;
import com.timthebrick.tinystorage.common.tileentity.implementations.TileEntityFilterChest;

public class ContainerFilterChest extends ContainerTinyStorage {

	// Small Chest
	private static final int SMALL_CHEST_INVENTORY_ROWS = 1;
	private static final int SMALL_CHEST_INVENTORY_COLUMNS = 8;
	public static final int SMALL_INVENTORY_SIZE = SMALL_CHEST_INVENTORY_ROWS * SMALL_CHEST_INVENTORY_COLUMNS;
	// Medium Chest
	private static final int MEDIUM_CHEST_INVENTORY_ROWS = 2;
	private static final int MEDIUM_CHEST_INVENTORY_COLUMNS = 8;
	public static final int MEDIUM_INVENTORY_SIZE = MEDIUM_CHEST_INVENTORY_ROWS * MEDIUM_CHEST_INVENTORY_COLUMNS;
	// Large Chest
	private static final int LARGE_CHEST_INVENTORY_ROWS = 3;
	private static final int LARGE_CHEST_INVENTORY_COLUMNS = 8;
	public static final int LARGE_INVENTORY_SIZE = LARGE_CHEST_INVENTORY_ROWS * LARGE_CHEST_INVENTORY_COLUMNS;

	private TileEntityFilterChest tileEntity;
	private InventoryPlayer inventory;
	private int chestInventoryRows;
	private int chestInventoryColumns;

	public ContainerFilterChest(InventoryPlayer inventoryPlayer, TileEntityFilterChest tileEntity) {
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

		// Add chest slots to inventory
		if (this.tileEntity.getState() == 0) {
			this.addSlotToContainer(new SlotFilter(tileEntity, 0, 8, 20));
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, x, 26 + (18 * x), 20));
				if (this.tileEntity.getStackInSlot(0) != null) {
					Slot slot = this.getSlot(x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(0));
					}
				}
			}
		} else if (this.tileEntity.getState() == 1) {
			for (int y = 0; y < 2; y++) {
				this.addSlotToContainer(new SlotFilter(tileEntity, y, 8, 20 + (18 * y)));
			}
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, 1 + x, 26 + (18 * x), 20));
				if (this.tileEntity.getStackInSlot(0) != null) {
					Slot slot = this.getSlot(1 + x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(0));
					}
				}
			}
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, 8 + x, 26 + (18 * x), 38));
				if (this.tileEntity.getStackInSlot(1) != null) {
					Slot slot = this.getSlot(8 + x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(1));
					}
				}
			}
		} else if (this.tileEntity.getState() == 2) {
			for (int y = 0; y < 3; y++) {
				this.addSlotToContainer(new SlotFilter(tileEntity, y, 8, 20 + (18 * y)));
			}
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, 2 + x, 26 + (18 * x), 20));
				if (this.tileEntity.getStackInSlot(0) != null) {
					Slot slot = this.getSlot(2 + x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(0));
					}
				}
			}
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, 9 + x, 26 + (18 * x), 38));
				if (this.tileEntity.getStackInSlot(1) != null) {
					Slot slot = this.getSlot(9 + x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(1));
					}
				}
			}
			for (int x = 1; x < 8; x++) {
				this.addSlotToContainer(new SlotRestrictedInput(tileEntity, 16 + x, 26 + (18 * x), 56));
				if (this.tileEntity.getStackInSlot(2) != null) {
					Slot slot = this.getSlot(16 + x);
					if (slot != null && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(this.tileEntity.getStackInSlot(2));
					}
				}
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
	public ItemStack slotClick(int slotNum, int mouseButton, int modifier, EntityPlayer player) {
		super.slotClick(slotNum, mouseButton, modifier, player);
		if (tileEntity.getState() == 0) {
			if (slotNum == 0) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(0)).getStack();
				for (int i = 1; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			}
		} else if (tileEntity.getState() == 1) {
			if (slotNum == 0) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(0)).getStack();
				for (int i = 2; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (i <= 8 && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			} else if (slotNum == 1) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(1)).getStack();
				for (int i = 2; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (i > 8 && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			}
		} else if (tileEntity.getState() == 2) {
			if (slotNum == 0) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(0)).getStack();
				for (int i = 0; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (i <= 9 && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			} else if (slotNum == 1) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(1)).getStack();
				for (int i = 0; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (i > 9 && i <= 16 && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			} else if (slotNum == 2) {
				ItemStack filterStack;
				filterStack = ((Slot) this.getSlot(2)).getStack();
				for (int i = 0; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (i > 16 && slot instanceof SlotRestrictedInput) {
						((SlotRestrictedInput) slot).setFilterStack(filterStack);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		tileEntity.closeInventory();
		if (!tileEntity.getWorldObj().isRemote) {
			if (tileEntity.numPlayersUsing == 0) {
				for (int i = 0; i < this.inventorySlots.size(); i++) {
					Slot slot = i < 0 ? null : (Slot) this.inventorySlots.get(i);
					if (slot instanceof SlotRestrictedInput) {
						if (((SlotRestrictedInput) slot).containsInvalidStack()) {
							ItemStack stack = slot.getStack();
							Random rand = new Random();

							float dX = rand.nextFloat() * 0.35F;
							float dY = rand.nextFloat() * 0.5F + 0.1F;
							float dZ = rand.nextFloat() * 0.35F;

							EntityItem entityItem = new EntityItem(tileEntity.getWorldObj(), tileEntity.xCoord + dX, tileEntity.yCoord + dY, tileEntity.zCoord + dZ, stack.copy());

							if (stack.hasTagCompound()) {
								entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
							}

							float factor = 0.05F;
							entityItem.motionX = rand.nextGaussian() * factor;
							entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
							entityItem.motionZ = rand.nextGaussian() * factor;
							tileEntity.getWorldObj().spawnEntityInWorld(entityItem);
							slot.putStack(null);
						}
					}
				}
			}
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		ItemStack newItemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);
		if (slot != null && slot instanceof IFakeItemSlot) {
			return null;
		}
		if (slot != null && slot.getHasStack()) {
			ItemStack itemStack = slot.getStack();
			newItemStack = itemStack.copy();

			if (slotIndex < chestInventoryRows * chestInventoryColumns) {
				if (!this.mergeItemStack(itemStack, chestInventoryRows * chestInventoryColumns, inventorySlots.size(), false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemStack, 0 + this.tileEntity.getState() + 1, chestInventoryRows * chestInventoryColumns, false)) {
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
