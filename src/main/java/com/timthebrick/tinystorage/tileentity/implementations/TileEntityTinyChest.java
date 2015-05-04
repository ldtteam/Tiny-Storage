package com.timthebrick.tinystorage.tileentity.implementations;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.init.ModBlocks;
import com.timthebrick.tinystorage.inventory.implementations.ContainerTinyChest;
import com.timthebrick.tinystorage.item.ItemStorageComponent;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.Sounds;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTinyChest extends TileEntityTinyStorage implements ISidedInventory {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	private int[] sides;

	public TileEntityTinyChest(int metaData) {
		super();
		this.state = (byte) metaData;
		if (metaData == 0) {
			inventory = new ItemStack[ContainerTinyChest.SMALL_INVENTORY_SIZE];
			sides = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		} else if (metaData == 1) {
			inventory = new ItemStack[ContainerTinyChest.MEDIUM_INVENTORY_SIZE];
			sides = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
		} else if (metaData == 2) {
			inventory = new ItemStack[ContainerTinyChest.LARGE_INVENTORY_SIZE];
			sides = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
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
			return Names.Containers.TINY_CHEST_LOCKED;
		} else {
			return Names.Containers.TINY_CHEST;
		}
	}
	
	public TileEntityTinyChest applyUpgradeItem(ItemStorageComponent itemStorageComponent, int upgradeTier, EntityPlayer player){
		if(this.hasUniqueOwner() && !this.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName())){
			return null;
		}
		if(numPlayersUsing > 0){
			return null;
		}
		TileEntityTinyChest newEntity;
		if(upgradeTier == 0){
			newEntity = new TileEntityTinyChestSmall();
		}else if(upgradeTier == 1){
			newEntity = new TileEntityTinyChestMedium();
		}else if(upgradeTier == 2){
			newEntity = new TileEntityTinyChestLarge();
		}else{
			return null;
		}
		int newSize = newEntity.inventory.length;
		System.arraycopy(inventory, 0, newEntity.inventory, 0, Math.min(newSize, inventory.length));
		newEntity.setOrientation(this.orientation);
		newEntity.ticksSinceSync = -1;
		if(this.hasUniqueOwner()){
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
		++numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		--numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack itemStack) {
		return true;
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
			worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_OPEN, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
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
				worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_CLOSE, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
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
	}

	@Override
	public void writeSyncedNBT(NBTTagCompound tag) {
		super.writeSyncedNBT(tag);
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
		return this.sides;
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
		if (this.accessMode == AccessMode.DISABLED) {
			return false;
		} else if (this.accessMode == AccessMode.INPUT_ONLY) {
			return this.isItemValidForSlot(slotID, stack);
		} else if (this.accessMode == AccessMode.OUTPUT_ONLY) {
			return false;
		} else if (this.accessMode == AccessMode.INPUT_OUTPUT) {
			return this.isItemValidForSlot(slotID, stack);
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
		if (this.accessMode == AccessMode.DISABLED) {
			return false;
		} else if (this.accessMode == AccessMode.INPUT_ONLY) {
			return false;
		} else if (this.accessMode == AccessMode.OUTPUT_ONLY) {
			return true;
		} else if (this.accessMode == AccessMode.INPUT_OUTPUT) {
			return true;
		}
		return false;
	}

}
