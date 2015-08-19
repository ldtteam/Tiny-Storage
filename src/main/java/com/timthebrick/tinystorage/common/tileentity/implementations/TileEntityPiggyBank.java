package com.timthebrick.tinystorage.common.tileentity.implementations;

import java.util.Random;

import com.timthebrick.tinystorage.common.reference.Messages;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.common.init.ModItems;
import com.timthebrick.tinystorage.common.item.ItemDebugTool;
import com.timthebrick.tinystorage.common.item.ItemStorageComponent;
import com.timthebrick.tinystorage.common.reference.Names;
import com.timthebrick.tinystorage.common.reference.Sounds;
import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankLarge;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankMedium;
import com.timthebrick.tinystorage.common.tileentity.implementations.sub.TileEntityPiggyBankSmall;
import com.timthebrick.tinystorage.util.common.ItemHelper;
import com.timthebrick.tinystorage.util.common.PlayerHelper;
import com.timthebrick.tinystorage.util.common.StackHelper;

public class TileEntityPiggyBank extends TileEntityTinyStorage implements ISidedInventory {

	private float headAngle;
	private float prevHeadAngle;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	private boolean shouldAction;
	private boolean headUp;
	private int bobbles;

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
	

	public TileEntityPiggyBank applyDowngradeClick(World world, ItemDebugTool itemDebugTool, int upgradeTier, EntityPlayer player) {
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
		int lossSize = this.inventory.length - newSize;
		ItemStack[] lossStacks = new ItemStack[lossSize];
		for (int i = newSize; i < this.inventory.length; i++) {
			lossStacks[i - newSize] = this.inventory[i];
		}
		Random rand = new Random();
		for (ItemStack itemStack : lossStacks) {
			if (itemStack != null && itemStack.stackSize > 0) {
				float dX = rand.nextFloat() * 0.8F + 0.1F;
				float dY = rand.nextFloat() * 0.8F + 0.1F;
				float dZ = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, this.xCoord + dX, this.yCoord + dY, this.zCoord + dZ, itemStack.copy());

				if (itemStack.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				itemStack.stackSize = 0;
			}
		}

		float dX = rand.nextFloat() * 0.8F + 0.1F;
		float dY = rand.nextFloat() * 0.8F + 0.1F;
		float dZ = rand.nextFloat() * 0.8F + 0.1F;

		EntityItem storageComponent = new EntityItem(world, this.xCoord + dX, this.yCoord + dY, this.zCoord + dZ, new ItemStack(ModItems.itemStorageUpgrade, 1, 0));

		float factor = 0.05F;
		storageComponent.motionX = rand.nextGaussian() * factor;
		storageComponent.motionY = rand.nextGaussian() * factor + 0.2F;
		storageComponent.motionZ = rand.nextGaussian() * factor;
		world.spawnEntityInWorld(storageComponent);
		
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
		//if(this.worldObj.difficultySetting.getDifficultyId() == EnumDifficulty.PEACEFUL)
		if (!this.worldObj.isRemote) {
			prevHeadAngle = headAngle;
			float angleIncrement = 1F;
			if (this.shouldAction) {
				angleIncrement = angleIncrement - (0.15F * 5 - bobbles);
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
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.markDirty();
			} else {
				if (headAngle > 0) {
					headAngle -= angleIncrement;
				} else if (headAngle < 0) {
					headAngle += angleIncrement;
				}
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.markDirty();
			}
			if (bobbles <= 0 && shouldAction) {
				bobbles = 0;
				shouldAction = false;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.markDirty();
			}
		}
	}

	public void handlePlayerInteraction(EntityPlayer player) {
		if (player.getHeldItem() != null) {
			if (this.canMergeItemStacks(player.getHeldItem())) {
				if (bobbles == 0) {
					shouldAction = (!shouldAction);
					double adjustedXCoord, adjustedZCoord;
					adjustedXCoord = xCoord + 0.5D;
					adjustedZCoord = zCoord + 0.5D;
					worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.PIG_SAY, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
					bobbles = 5;
					int originalSize = player.getHeldItem().copy().stackSize;
					player.getHeldItem().stackSize = originalSize - tryMergeStacks(player.getHeldItem()).stackSize;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.markDirty();
				}
			} else if (this.hasInventorySpace()) {
				int invSlot = getNextFreeSlot();
				if (invSlot >= 0) {
					if (bobbles == 0) {
						shouldAction = (!shouldAction);
						double adjustedXCoord, adjustedZCoord;
						adjustedXCoord = xCoord + 0.5D;
						adjustedZCoord = zCoord + 0.5D;
						worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.PIG_SAY, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
						bobbles = 5;
						this.setInventorySlotContents(invSlot, player.getHeldItem().copy());
						player.getHeldItem().stackSize = 0;
						this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						this.markDirty();
					}
				}
			} else {
				PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.PIGGY_BANK_FULL));
			}
		} else {
			if (this.getNextFreeSlot() == 0) {
				PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.PIGGY_BANK_EMPTY));
			} else {
				PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.PIGGY_BANK_CURRENT_CONTENTS));
				for (int i = 0; i < this.getNextFreeSlot(); i++) {
					PlayerHelper.sendChatMessage(player, getStackInSlot(i).getDisplayName() + " : " + getStackInSlot(i).stackSize);
				}
			}
		}
	}

	public void handBadPlayerInteraction(EntityPlayer player) {
		double adjustedXCoord, adjustedZCoord;
		adjustedXCoord = xCoord + 0.5D;
		adjustedZCoord = zCoord + 0.5D;
		PlayerHelper.sendChatMessage(player, new ChatComponentTranslation(Messages.Chat.PIGGY_BANK_NOT_OWNED));
		worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.PIG_DEATH, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
	}

	public boolean hasInventorySpace() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (getStackInSlot(i) == null) {
				return true;
			}
		}
		return false;
	}

	public int getNextFreeSlot() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (getStackInSlot(i) == null) {
				return i;
			}
		}
		return -1;
	}

	public boolean canMergeItemStacks(ItemStack input) {
		for (int i = 0; i < getNextFreeSlot(); i++) {
			if (ItemHelper.equalsIgnoreStackSize(input, getStackInSlot(i)) && getStackInSlot(i).stackSize < getInventoryStackLimit()) {
				return true;
			}
		}
		return false;
	}

	public ItemStack tryMergeStacks(ItemStack input) {
		ItemStack leftOver = null;
		for (int i = 0; i < getNextFreeSlot(); i++) {
			if (ItemHelper.equalsIgnoreStackSize(input, getStackInSlot(i)) && getStackInSlot(i).stackSize < getInventoryStackLimit()) {
				leftOver = input.copy();
				leftOver.stackSize = StackHelper.mergeStacks(input, getStackInSlot(i), true);
				this.markDirty();
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
		return leftOver;
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
		this.headAngle = tag.getFloat("HeadAngle");
	}

	@Override
	public void writeSyncedNBT(NBTTagCompound tag) {
		super.writeSyncedNBT(tag);
		tag.setBoolean("Action", shouldAction);
		tag.setInteger("Bobbles", bobbles);
		tag.setFloat("HeadAngle", headAngle);
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
