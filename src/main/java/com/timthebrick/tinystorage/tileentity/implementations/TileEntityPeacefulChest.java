package com.timthebrick.tinystorage.tileentity.implementations;

import java.util.List;
import java.util.Random;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.inventory.implementations.ContainerPeacefulChest;
import com.timthebrick.tinystorage.item.ItemStorageComponent;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.Sounds;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.InventoryHelper;
import com.timthebrick.tinystorage.util.ItemHelper;
import com.timthebrick.tinystorage.util.Settings;
import com.timthebrick.tinystorage.util.StackHelper;

public class TileEntityPeacefulChest extends TileEntityTinyStorage implements ISidedInventory {

	public float lidAngle;
	public float prevLidAngle;
	public int numPlayersUsing;
	private int ticksSinceSync;
	private ItemStack[] inventory;
	private int[] inventorySlots;
	private int swordSlot;
	private int cooldownTicks;
	private int ticksSinceAction;
	private Random random;

	public TileEntityPeacefulChest(int metaData) {
		super();
		swordSlot = 0;
		this.state = (byte) metaData;
		if (metaData == 0) {
			inventory = new ItemStack[ContainerPeacefulChest.SMALL_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		} else if (metaData == 1) {
			inventory = new ItemStack[ContainerPeacefulChest.MEDIUM_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		} else if (metaData == 2) {
			inventory = new ItemStack[ContainerPeacefulChest.LARGE_INVENTORY_SIZE];
			inventorySlots = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
		}
		this.random = new Random();
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
			return Names.Containers.PEACEFUL_CHEST_LOCKED;
		} else {
			return Names.Containers.PEACEFUL_CHEST;
		}
	}

	public TileEntityPeacefulChest applyUpgradeItem(ItemStorageComponent itemStorageComponent, int upgradeTier, EntityPlayer player) {
		if (this.hasUniqueOwner() && !this.getUniqueOwner().equals(player.getUniqueID().toString() + player.getDisplayName())) {
			return null;
		}
		if (numPlayersUsing > 0) {
			return null;
		}
		TileEntityPeacefulChest newEntity;
		if (upgradeTier == 0) {
			newEntity = new TileEntityPeacefulChestSmall();
		} else if (upgradeTier == 1) {
			newEntity = new TileEntityPeacefulChestMedium();
		} else if (upgradeTier == 2) {
			newEntity = new TileEntityPeacefulChestLarge();
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
		++numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		--numPlayersUsing;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
	}

	private boolean hasSwordInUse() {
		return ((this.getStackInSlot(swordSlot) != null) && (this.getStackInSlot(swordSlot).getItem() instanceof ItemSword));
	}

	private ItemStack getSwordInUse() {
		return hasSwordInUse() ? getStackInSlot(swordSlot) : null;
	}

	private boolean hasInventorySpace() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (getStackInSlot(i) == null) {
				return true;
			}
		}
		return false;
	}

	private int getNextFreeSlot() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (getStackInSlot(i) == null) {
				return i;
			}
		}
		return -1;
	}

	private boolean canMergeItemStacks(ItemStack input) {
		for (int i = 0; i < getNextFreeSlot(); i++) {
			if (ItemHelper.equalsIgnoreStackSize(input, getStackInSlot(i)) == true && getStackInSlot(i).stackSize < getInventoryStackLimit()) {
				return true;
			}
		}
		return false;
	}

	private ItemStack tryMergeStacks(ItemStack input) {
		ItemStack leftOver = null;
		for (int i = 0; i < getNextFreeSlot(); i++) {
			if (ItemHelper.equalsIgnoreStackSize(input, getStackInSlot(i)) == true && getStackInSlot(i).stackSize < getInventoryStackLimit()) {
				leftOver = input.copy();
				leftOver.stackSize = StackHelper.mergeStacks(input, getStackInSlot(i), true);
				this.markDirty();
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
		return leftOver;
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

		if (!this.worldObj.isRemote) {

			if ((Settings.Blocks.peacefulChestMode == false) && (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)) {
				return;
			}

			if (this.hasInventorySpace() && !this.worldObj.isDaytime()) {
				if (cooldownTicks - ticksSinceAction == 0) {

					BiomeGenBase.SpawnListEntry mobSpawn = ((WorldServer) worldObj).spawnRandomCreature(EnumCreatureType.monster, xCoord, yCoord, zCoord);

					if (mobSpawn != null) {

						if (!this.hasSwordInUse()) {
							return;
						}

						ItemStack sword = getSwordInUse();
						EntityLiving mobEntity;

						try {
							mobEntity = (EntityLiving) mobSpawn.entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldObj });
						} catch (Exception e) {
							TinyStorageLog.error(e);
							return;
						}

						List itemEntities = worldObj.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord, zCoord - 2, xCoord + 3, yCoord + 4, zCoord + 2), IEntitySelector.selectAnything);

						mobEntity.setLocationAndAngles(xCoord + 0.5D, yCoord + 1.25D, zCoord + 0.5D, random.nextFloat() * 360.0F, 0.0F);
						worldObj.spawnEntityInWorld(mobEntity);

						EnumDifficulty prevDifficulty = worldObj.difficultySetting;
						worldObj.difficultySetting = EnumDifficulty.HARD;
						mobEntity.onSpawnWithEgg(null);
						worldObj.difficultySetting = prevDifficulty;

						FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((WorldServer) worldObj);

						fakePlayer.setCurrentItemOrArmor(0, sword.copy());
						float healthA = mobEntity.getHealth();
						fakePlayer.attackTargetEntityWithCurrentItem(mobEntity);
						float healthB = mobEntity.getHealth();

						if (mobEntity.isDead) {
							if ((fakePlayer.getCurrentEquippedItem() == null) || (fakePlayer.getCurrentEquippedItem().stackSize == 0)) {
								this.setInventorySlotContents(swordSlot, null);
							} else {
								this.setInventorySlotContents(swordSlot, fakePlayer.getCurrentEquippedItem());
							}
						} else {
							if ((fakePlayer.getCurrentEquippedItem() == null) || (fakePlayer.getCurrentEquippedItem().stackSize == 0)) {
								this.setInventorySlotContents(swordSlot, null);
							} else {
								if (healthA > healthB) {
									for (float health = healthB; health > 0.0F; health -= healthA - healthB) {
										sword.hitEntity(mobEntity, fakePlayer);
									}
								}
								if (sword.stackSize == 0) {
									this.setInventorySlotContents(swordSlot, null);
								}
							}
							mobEntity.onDeath(DamageSource.causePlayerDamage(fakePlayer));
							mobEntity.motionX = 0.0D;
							mobEntity.motionY = 0.0D;
							mobEntity.motionZ = 0.0D;
						}

						fakePlayer.setCurrentItemOrArmor(0, null);
						mobEntity.setDead();

						List newItemEntities = worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord, zCoord - 2, xCoord + 3, yCoord + 4, zCoord + 2));

						for (Object obj : newItemEntities) {
							if (!itemEntities.contains(obj)) {
								if (!(((EntityItem) obj).isDead)) {
									ItemStack itemStack = ((EntityItem) obj).getEntityItem().copy();
									ItemStack itemstack1 = InventoryHelper.invInsert(this, itemStack, 2);
									if ((itemstack1 != null) && (itemstack1.stackSize != 0)) {
										((EntityItem) obj).setEntityItemStack(itemstack1);
									} else {
										((EntityItem) obj).setDead();
									}
								}
							}
						}
						cooldownTicks = 100 + random.nextInt(100);
						ticksSinceAction = 0;
					}
				} else {
					ticksSinceAction++;

				}
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
		cooldownTicks = tagCompound.getInteger("cooldownTicks");
		ticksSinceAction = tagCompound.getInteger("ticksSinceAction");
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
		tagCompound.setInteger("cooldownTicks", cooldownTicks);
		tagCompound.setInteger("ticksSinceAction", ticksSinceAction);
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
	public boolean isItemValidForSlot(int slotID, ItemStack itemStack) {
		if (slotID == swordSlot) {
			if (itemStack.getItem() instanceof ItemSword) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == 2 || side == 3 || side == 4 || side == 5) {
			return this.inventorySlots;
		}
		return new int[] { swordSlot };
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int blockSide) {
		return true;
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int blockSide) {
		if (slotID == swordSlot) {
			return false;
		}
		return true;
	}

}
