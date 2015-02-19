package com.timthebrick.tinystorage.tileentity;

import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.inventory.ContainerFilterChest;
import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.util.StackHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityFilterChest extends TileEntityTinyStorage implements ISidedInventory {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	private int[] sides;

	public TileEntityFilterChest(int metaData) {
		super();
		this.state = (byte) metaData;
		if (metaData == 0) {
			inventory = new ItemStack[ContainerFilterChest.SMALL_INVENTORY_SIZE];
			sides = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		} else if (metaData == 1) {
			inventory = new ItemStack[ContainerFilterChest.MEDIUM_INVENTORY_SIZE];
			sides = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		} else if (metaData == 2) {			
			inventory = new ItemStack[ContainerFilterChest.LARGE_INVENTORY_SIZE];
			sides = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
		}
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return inventory[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null) {
			if (itemStack.stackSize <= decrementAmount) {
				setInventorySlotContents(slotIndex, null);
			} else {
				itemStack = itemStack.splitStack(decrementAmount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slotIndex, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (inventory[slotIndex] != null) {
			ItemStack itemStack = inventory[slotIndex];
			inventory[slotIndex] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
		inventory[slotIndex] = itemStack;
		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();
	}

	@Override
	public String getInventoryName() {
		if (this.hasCustomName()) {
			return this.getCustomName();
		} else if (this.hasUniqueOwner()) {
			return Names.Containers.FILTER_CHEST;
		} else {
			return Names.Containers.FILTER_CHEST_LOCKED;
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.hasCustomName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
		++numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		--numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack stack) {
		if (this.getState() == 0) {
			if (slotID == 0) {
				return false;
			} else {
				return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
			}
		} else if (this.getState() == 1) {
			if (slotID == 0 || slotID == 1) {
				return false;
			} else {
				if (slotID > 1 && slotID <= 8) {
					return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
				} else {
					return StackHelper.isMatchingItem(stack, getStackInSlot(1), true, true);
				}
			}
		} else if (this.getState() == 2) {
			if (slotID == 0 || slotID == 1 || slotID == 2) {
				return false;
			} else {
				if (slotID > 2 && slotID <= 9) {
					return StackHelper.isMatchingItem(stack, getStackInSlot(0), true, true);
				} else if (slotID > 9 && slotID <= 16 ){
					return StackHelper.isMatchingItem(stack, getStackInSlot(1), true, true);
				}else{
					return StackHelper.isMatchingItem(stack, getStackInSlot(2), true, true);
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (++ticksSinceSync % 20 * 4 == 0) {
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
		}

		prevLidAngle = lidAngle;
		float angleIncrement = 0.1F;
		double adjustedXCoord, adjustedZCoord;

		if (numPlayersUsing > 0 && lidAngle == 0.0F) {
			adjustedXCoord = xCoord + 0.5D;
			adjustedZCoord = zCoord + 0.5D;
		}

		if (numPlayersUsing == 0 && lidAngle > 0.0F || numPlayersUsing > 0 && lidAngle < 1.0F) {
			float var8 = lidAngle;

			if (numPlayersUsing > 0) {
				lidAngle += angleIncrement;
			} else {
				lidAngle -= angleIncrement;
			}

			if (lidAngle > 1.0F) {
				lidAngle = 1.0F;
			}

			if (lidAngle < 0.5F && var8 >= 0.5F) {
				adjustedXCoord = xCoord + 0.5D;
				adjustedZCoord = zCoord + 0.5D;
			}

			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int eventID, int numUsingPlayers) {
		if (eventID == 1) {
			this.numPlayersUsing = numUsingPlayers;
			return true;
		} else {
			return super.receiveClientEvent(eventID, numUsingPlayers);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return this.sides;
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
		return this.isItemValidForSlot(slotID, stack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
		return true;
	}

}
