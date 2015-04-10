package com.timthebrick.tinystorage.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.inventory.ContainerTinyChest;
import com.timthebrick.tinystorage.item.ItemStorageComponent;
import com.timthebrick.tinystorage.reference.Names;

public class TileEntityPiggyBank extends TileEntityTinyStorage implements ISidedInventory {

	public float headAngle;
	public float prevHeadAngle;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	boolean shouldAction;
	boolean headUp;
	int bobbles;

	public TileEntityPiggyBank(int metaData) {
		super();
		this.state = (byte) metaData;
		if (metaData == 0) {
			inventory = new ItemStack[3];
		} else if (metaData == 1) {
			inventory = new ItemStack[6];
		} else if (metaData == 2) {
			inventory = new ItemStack[9];
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
		} else {
			return Names.Containers.PIGGY_BANK;
		}
	}

	public TileEntityPiggyBank applyUpgradeItem(ItemStorageComponent itemStorageComponent, int upgradeTier, EntityPlayer player) {
		if (this.hasUniqueOwner() && !this.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName())) {
			return null;
		}
		TileEntityPiggyBank newEntity;
		if (upgradeTier == 0) {
			newEntity = new TileEntityPiggyBankSmall();
		} else if (upgradeTier == 1) {
			newEntity = new TileEntityPiggyBankMedium();
		} else if (upgradeTier == 2) {
			newEntity = new TileEntityPiggyBankLarge();
		} else {
			return null;
		}
		int newSize = newEntity.inventory.length;
		System.arraycopy(inventory, 0, newEntity.inventory, 0, Math.min(newSize, inventory.length));
		newEntity.setOrientation(this.orientation);
		newEntity.ticksSinceSync = -1;
		if (this.hasUniqueOwner()) {
			newEntity.setUniqueOwner(player);
			newEntity.setOwner(player);
		}
		return newEntity;
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
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack stack) {
		return false;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote) {
			prevHeadAngle = headAngle;
			float angleIncrement = 1F;
			// System.out.println(getAction());
			if (this.shouldAction) {
				angleIncrement = angleIncrement -  0.15F;
				if (headUp) {
					headAngle += angleIncrement;
				} else {
					headAngle -= angleIncrement;
				}

				if (headAngle > 10.0F) {
					headAngle = 10.0F;
					headUp = false;
					bobbles--;
				}

				if (headAngle < -10F) {
					headAngle = -10F;
					headUp = true;
					bobbles--;
				}
				// System.out.println(prevHeadAngle + " | " + headAngle + " | "
				// + this.getAction());
			} else {
				if (headAngle > 0) {
					headAngle -= angleIncrement;
				} else if (headAngle < 0) {
					headAngle += angleIncrement;
				}
			}
			if (bobbles <= 0 && shouldAction) {
				bobbles = 0;
				shouldAction = false;
				TinyStorageLog.info("Stopping bobbling");
			}
			markDirty();
		}
	}

	public void setAction() {
		if(bobbles == 0){
			shouldAction = (!shouldAction);
			bobbles = 5;
		}
		this.markDirty();
	}

	public float getHeadAngle() {
		return headAngle;
	}

	@Override
	public boolean receiveClientEvent(int eventID, int numUsingPlayers) {
		return super.receiveClientEvent(eventID, numUsingPlayers);
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
		readSyncedNBT(tagCompound);
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
		writeSyncedNBT(tagCompound);
	}

	@Override
	public void readSyncedNBT(NBTTagCompound tag) {
		super.readSyncedNBT(tag);
		this.shouldAction = tag.getBoolean("Action");
		this.bobbles = tag.getInteger("Bobbles");
	}

	@Override
	public void writeSyncedNBT(NBTTagCompound tag) {
		super.writeSyncedNBT(tag);
		tag.setBoolean("Action", shouldAction);
		tag.setInteger("Bobbles", bobbles);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncedNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readSyncedNBT(pkt.func_148857_g());
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
		return false;
	}

}
