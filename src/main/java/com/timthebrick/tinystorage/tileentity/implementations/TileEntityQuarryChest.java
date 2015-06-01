package com.timthebrick.tinystorage.tileentity.implementations;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.inventory.implementations.ContainerQuarryChest;
import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.MessageSpawnParticle;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.reference.Sounds;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.*;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TileEntityQuarryChest extends TileEntityTinyStorage implements ISidedInventory {

    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private ItemStack[] inventory;
    private int[] sides;
    private Random random;
    private int cooldown;
    private int blockBreakCooldown;
    private boolean running = true;
    private int opRadius, opDepth;
    private int currentY = 1;
    private int currentBlockX, currentBlockY, currentBlockZ;

    public TileEntityQuarryChest (int metaData) {
        super();
        this.state = (byte) metaData;
        if (metaData == 0) {
            inventory = new ItemStack[ContainerQuarryChest.SMALL_INVENTORY_SIZE];
            sides = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
            opRadius = 10;
            opDepth = 10;
        } else if (metaData == 1) {
            inventory = new ItemStack[ContainerQuarryChest.MEDIUM_INVENTORY_SIZE];
            sides = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
            opRadius = 20;
            opDepth = 20;
        } else if (metaData == 2) {
            inventory = new ItemStack[ContainerQuarryChest.LARGE_INVENTORY_SIZE];
            sides = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
            opRadius = 40;
            opDepth = 40;
        }
        random = new Random();
    }

    @Override
    public int getSizeInventory () {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot (int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize (int slotIndex, int decrementAmount) {
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
    public ItemStack getStackInSlotOnClosing (int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents (int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName () {
        if (this.hasCustomName()) {
            return this.getCustomName();
        } else {
            return Names.Containers.QUARRY_CHEST;
        }
    }

    @Override
    public boolean hasCustomInventoryName () {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit () {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer (EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory () {
        ++numPlayersUsing;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public void closeInventory () {
        --numPlayersUsing;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, this.worldObj.getBlock(xCoord, yCoord, zCoord), 1, numPlayersUsing);
    }

    @Override
    public boolean isItemValidForSlot (int slotID, ItemStack itemStack) {
        return true;
    }

    EntryMap<String, ArrayList<ItemStack>> currentLayer;

    @Override
    public void updateEntity () {

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

        if (!worldObj.isRemote) {
            if (running) {
                if (currentY <= opDepth) {
                    if (currentLayer != null) {
                        if (!currentLayer.isEmpty()) {
                            if (cooldown <= 0) {
                                if (this.hasInventorySpace()) {
                                    Iterator<String> iter = currentLayer.keySet().iterator();
                                    String locationEncoded = iter.next();
                                    String[] location = locationEncoded.split(";");
                                    ArrayList<ItemStack> drops = currentLayer.get(locationEncoded);
                                    ArrayList<ItemStack> checkDrops = worldObj.getBlock(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2])).getDrops(worldObj, Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2]), worldObj.getBlockMetadata(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2])), 0);
                                    boolean possibleMatch = false;
                                    int matchedItems = 0;
                                    int totalItems = 0;
                                    for (ItemStack stack : checkDrops) {
                                        for (ItemStack stackA : drops) {
                                            if (ItemHelper.equalsIgnoreStackSize(stack, stackA)) {
                                                possibleMatch = true;
                                                matchedItems++;
                                            }
                                        }
                                        totalItems++;
                                    }
                                    if (checkDrops.size() > drops.size() || matchedItems / totalItems < 0.75) {
                                        possibleMatch = false;
                                    }
                                    if (!possibleMatch) {
                                        drops = checkDrops;
                                    }
                                    if (worldObj.getTileEntity(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2])) != null && worldObj.getTileEntity(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2])) instanceof IInventory) {
                                        TinyStorageLog.info("Found an inventory");
                                        IInventory inventoryToCheck = (IInventory) worldObj.getTileEntity(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2]));
                                        for (int i = 0; i < inventoryToCheck.getSizeInventory(); i++) {
                                            if (inventoryToCheck.getStackInSlot(i) != null && inventoryToCheck.getStackInSlot(i).stackSize > 0) {
                                                //TinyStorageLog.info("Inside loop " + i);
                                                drops.add(inventoryToCheck.getStackInSlot(i).copy());
                                                inventoryToCheck.setInventorySlotContents(i, null);
                                            }
                                        }
                                    }
                                    if (drops != null) {
                                        for (ItemStack item : drops) {
                                            TinyStorageLog.info(item.getItem().getUnlocalizedName());
                                            ItemStack item1 = InventoryHelper.invInsert(this, item, 0);
                                            if ((item1 != null) && (item1.stackSize != 0)) {
                                                WorldHelper.spawnEntityItemWithRandomMovement(item1, worldObj, xCoord, yCoord, zCoord);
                                            }
                                        }
                                    }
                                    worldObj.notifyBlockOfNeighborChange(Integer.parseInt(location[0]), Integer.parseInt(location[1]) + 1, Integer.parseInt(location[2]), worldObj.getBlock(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2])));
                                    worldObj.setBlockToAir(Integer.parseInt(location[0]), Integer.parseInt(location[1]), Integer.parseInt(location[2]));
                                    currentLayer.remove(locationEncoded);
                                }
                                cooldown = 60 - (int) ((getInventoryMass() / (getSizeInventory() * 64)) * 60);
                                blockBreakCooldown = cooldown;
                            } else {
                                Iterator<String> iter = currentLayer.keySet().iterator();
                                String locationEncoded = iter.next();
                                String[] location = locationEncoded.split(";");
                                currentBlockX = Integer.parseInt(location[0]);
                                currentBlockY = Integer.parseInt(location[1]);
                                currentBlockZ = Integer.parseInt(location[2]);
                                PacketHandler.INSTANCE.sendToAll(new MessageSpawnParticle(Minecraft.getMinecraft().thePlayer, xCoord, yCoord, zCoord, currentBlockX, currentBlockY, currentBlockZ));
                                //TinyStorageLog.info(currentBlockX + ", " + currentBlockY + ", " + currentBlockZ);
                                cooldown--;
                                blockBreakCooldown = cooldown;
                            }
                        } else {
                            currentY += 1;
                            currentLayer = CircleHelper.genCircle(xCoord, yCoord - currentY, zCoord, worldObj, opRadius);
                        }
                    } else {
                        currentLayer = CircleHelper.genCircle(xCoord, yCoord - currentY, zCoord, worldObj, opRadius);
                    }
                    this.markDirty();
                    this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                } else {
                    running = false;
                }
            }
        }
    }

    public void spawnDigParticle (float xCoord, float yCoord, float zCoord, float dX, float dY, float dZ) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world.isRemote) {
            Vector3 velocity = MathHelper.getMotionFromPosition(new Vector3(dX, dY, dZ), new Vector3(xCoord, yCoord, zCoord), 1F).multiply(new Vector3(-5, -5, -5));
            world.spawnParticle("portal", xCoord, yCoord, zCoord, velocity.x, velocity.y, velocity.z);
            world.spawnParticle("portal", xCoord, yCoord, zCoord, velocity.x, velocity.y, velocity.z);
            world.spawnParticle("portal", xCoord, yCoord, zCoord, velocity.x, velocity.y, velocity.z);
        }
    }

    private float getInventoryMass () {
        float mass = 0;
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize > 0) {
                mass += getStackInSlot(i).stackSize;
            }
        }
        return mass;
    }

    private boolean hasInventorySpace () {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) == null) {
                return true;
            }
        }
        return false;
    }

    public void genFirstLayer () {
        currentLayer = CircleHelper.genCircle(xCoord, yCoord - currentY, zCoord, worldObj, opRadius);
    }

    @Override
    public boolean receiveClientEvent (int eventID, int numUsingPlayers) {
        if (eventID == 1) {
            this.numPlayersUsing = numUsingPlayers;
            return true;
        } else {
            return super.receiveClientEvent(eventID, numUsingPlayers);
        }
    }

    @Override
    public void readFromNBT (NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        currentY = tagCompound.getInteger("currentY");
        cooldown = tagCompound.getInteger("cooldown");
        running = tagCompound.getBoolean("running");
        opDepth = tagCompound.getInteger("opDepth");
        opRadius = tagCompound.getInteger("opRadius");
        currentBlockX = tagCompound.getInteger("currentBlockX");
        currentBlockY = tagCompound.getInteger("currentBlockY");
        currentBlockZ = tagCompound.getInteger("currentBlockZ");
        readSyncedNBT(tagCompound);
    }

    @Override
    public void writeToNBT (NBTTagCompound tagCompound) {
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
        tagCompound.setInteger("currentY", currentY);
        tagCompound.setInteger("cooldown", cooldown);
        tagCompound.setBoolean("running", running);
        tagCompound.setInteger("opDepth", opDepth);
        tagCompound.setInteger("opRadius", opRadius);
        tagCompound.setInteger("currentBlockX", currentBlockX);
        tagCompound.setInteger("currentBlockY", currentBlockY);
        tagCompound.setInteger("currentBlockZ", currentBlockZ);
        writeSyncedNBT(tagCompound);
    }

    @Override
    public void readSyncedNBT (NBTTagCompound tag) {
        super.readSyncedNBT(tag);
        blockBreakCooldown = tag.getInteger("blockCooldown");

    }

    @Override
    public void writeSyncedNBT (NBTTagCompound tag) {
        super.writeSyncedNBT(tag);
        tag.setInteger("blockCooldown", blockBreakCooldown);

    }

    @Override
    public Packet getDescriptionPacket () {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncedNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket (NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readSyncedNBT(pkt.func_148857_g());
    }

    @Override
    public int[] getAccessibleSlotsFromSide (int side) {
        return this.sides;
    }

    @Override
    public boolean canInsertItem (int slotID, ItemStack stack, int blockSide) {
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
    public boolean canExtractItem (int slotID, ItemStack stack, int blockSide) {
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
