package com.timthebrick.tinystorage.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.inventory.slot.IFakeItemSlot;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.ItemHelper;
import com.timthebrick.tinystorage.util.StackHelper;

public class ContainerTinyStorage extends Container {

	protected final int PLAYER_INVENTORY_ROWS = 3;
	protected final int PLAYER_INVENTORY_COLUMNS = 9;

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return true;
	}
	@Override
	protected boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean ascending) {
		boolean slotFound = false;
		int currentSlotIndex = ascending ? slotMax - 1 : slotMin;

		Slot slot;
		ItemStack stackInSlot;

		if (itemStack.isStackable()) {
			while (itemStack.stackSize > 0 && (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin)) {
				slot = (Slot) this.inventorySlots.get(currentSlotIndex);
				stackInSlot = slot.getStack();

				if (slot.isItemValid(itemStack) && ItemHelper.equalsIgnoreStackSize(itemStack, stackInSlot)) {
					int combinedStackSize = stackInSlot.stackSize + itemStack.stackSize;
					int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());

					if (combinedStackSize <= slotStackSizeLimit) {
						itemStack.stackSize = 0;
						stackInSlot.stackSize = combinedStackSize;
						slot.onSlotChanged();
						slotFound = true;
					} else if (stackInSlot.stackSize < slotStackSizeLimit) {
						itemStack.stackSize -= slotStackSizeLimit - stackInSlot.stackSize;
						stackInSlot.stackSize = slotStackSizeLimit;
						slot.onSlotChanged();
						slotFound = true;
					}
				}

				currentSlotIndex += ascending ? -1 : 1;
			}
		}

		if (itemStack.stackSize > 0) {
			currentSlotIndex = ascending ? slotMax - 1 : slotMin;

			while (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin) {
				slot = (Slot) this.inventorySlots.get(currentSlotIndex);
				stackInSlot = slot.getStack();

				if (slot.isItemValid(itemStack) && stackInSlot == null) {
					slot.putStack(ItemHelper.cloneItemStack(itemStack, Math.min(itemStack.stackSize, slot.getSlotStackLimit())));
					slot.onSlotChanged();

					if (slot.getStack() != null) {
						itemStack.stackSize -= slot.getStack().stackSize;
						slotFound = true;
					}

					break;
				}

				currentSlotIndex += ascending ? -1 : 1;
			}
		}

		return slotFound;
	}
	
	/**
	 * Based upon the Buildcraft fake slot methods
	 */
	@Override
	public ItemStack slotClick(int slotNum, int mouseButton, int modifier, EntityPlayer player) {
		Slot slot = slotNum < 0 ? null : (Slot) this.inventorySlots.get(slotNum);
		if (slot instanceof IFakeItemSlot) {
			return slotClickFakeItem(slot, mouseButton, modifier, player);
		}
		return super.slotClick(slotNum, mouseButton, modifier, player);
	}
	
	/**
	 * Based upon the Buildcraft fake slot methods
	 * @param slot
	 * @param mouseButton
	 * @param modifier
	 * @param player
	 * @return
	 */
	private ItemStack slotClickFakeItem(Slot slot, int mouseButton, int modifier, EntityPlayer player) {
		ItemStack stack = null;
		if (mouseButton == 2) {
			if (((IFakeItemSlot) slot).canAdjust()) {
				slot.putStack(null);
			}
		} else if (mouseButton == 0 || mouseButton == 1) {
			InventoryPlayer playerInv = player.inventory;
			slot.onSlotChanged();
			ItemStack stackSlot = slot.getStack();
			ItemStack stackHeld = playerInv.getItemStack();

			if (stackSlot != null) {
				stack = stackSlot.copy();
			}

			if (stackSlot == null) {
				if (stackHeld != null && slot.isItemValid(stackHeld)) {
					fillFakeItemSlot(slot, stackHeld, mouseButton, modifier);
				}
			} else if (stackHeld == null) {
				adjustFakeItemSlot(slot, mouseButton, modifier);
				slot.onPickupFromSlot(player, playerInv.getItemStack());
			} else if (slot.isItemValid(stackHeld)) {
				if (StackHelper.canStacksMerge(stackSlot, stackHeld)) {
					adjustFakeItemSlot(slot, mouseButton, modifier);
				} else {
					fillFakeItemSlot(slot, stackHeld, mouseButton, modifier);
				}
			}
		}
		return stack;
	}

	/**
	 * Based upon the Buildcraft fake slot methods
	 * @param slot
	 * @param mouseButton
	 * @param modifier
	 */
	private void adjustFakeItemSlot(Slot slot, int mouseButton, int modifier) {
		if (!((IFakeItemSlot) slot).canAdjust()) {
			return;
		}
		ItemStack stackSlot = slot.getStack();
		int stackSize;
		if (modifier == 1) {
			stackSize = mouseButton == 0 ? (stackSlot.stackSize + 1) / 2 : stackSlot.stackSize * 2;
		} else {
			stackSize = mouseButton == 0 ? stackSlot.stackSize - 1 : stackSlot.stackSize + 1;
		}

		if (stackSize > slot.getSlotStackLimit()) {
			stackSize = slot.getSlotStackLimit();
		}

		stackSlot.stackSize = stackSize;

		if (stackSlot.stackSize <= 0) {
			slot.putStack(null);
		}
	}
	
	/**
	 * Based upon the Buildcraft fake slot methods
	 * @param slot
	 * @param stackHeld
	 * @param mouseButton
	 * @param modifier
	 */
	private void fillFakeItemSlot(Slot slot, ItemStack stackHeld, int mouseButton, int modifier) {
		if (!((IFakeItemSlot) slot).canAdjust()) {
			return;
		}
		int stackSize = mouseButton == 0 ? stackHeld.stackSize : 1;
		if (stackSize > slot.getSlotStackLimit()) {
			stackSize = slot.getSlotStackLimit();
		}
		ItemStack phantomStack = stackHeld.copy();
		phantomStack.stackSize = stackSize;

		slot.putStack(phantomStack);
	}

}
