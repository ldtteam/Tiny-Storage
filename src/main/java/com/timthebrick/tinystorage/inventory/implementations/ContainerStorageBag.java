package com.timthebrick.tinystorage.inventory.implementations;

import com.timthebrick.tinystorage.inventory.ContainerTinyStorage;
import com.timthebrick.tinystorage.inventory.slot.SlotStorageBag;
import com.timthebrick.tinystorage.item.ItemStorageBag;
import com.timthebrick.tinystorage.reference.Names;
import com.timthebrick.tinystorage.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ContainerStorageBag extends ContainerTinyStorage {

    // Small Bag
    public static final int SMALL_BAG_INVENTORY_ROWS = 1;
    public static final int SMALL_BAG_INVENTORY_COLUMNS = 9;
    public static final int SMALL_INVENTORY_SIZE = SMALL_BAG_INVENTORY_ROWS * SMALL_BAG_INVENTORY_COLUMNS;
    // Medium Chest
    public static final int MEDIUM_BAG_INVENTORY_ROWS = 2;
    public static final int MEDIUM_BAG_INVENTORY_COLUMNS = 9;
    public static final int MEDIUM_INVENTORY_SIZE = MEDIUM_BAG_INVENTORY_ROWS * MEDIUM_BAG_INVENTORY_COLUMNS;
    // Large Chest
    public static final int LARGE_BAG_INVENTORY_ROWS = 3;
    public static final int LARGE_BAG_INVENTORY_COLUMNS = 9;
    public static final int LARGE_INVENTORY_SIZE = LARGE_BAG_INVENTORY_ROWS * LARGE_BAG_INVENTORY_COLUMNS;

    private final EntityPlayer entityPlayer;
    public final InventoryStorageBag inventoryStorageBag;

    private int bagInventoryRows;
    private int bagInventoryColumns;

    public ContainerStorageBag (EntityPlayer entityPlayer, InventoryStorageBag inventoryStorageBag) {
        this.entityPlayer = entityPlayer;
        this.inventoryStorageBag = inventoryStorageBag;

        if (inventoryStorageBag.parentItemStack.getItemDamage() == 0) {
            bagInventoryRows = SMALL_BAG_INVENTORY_ROWS;
            bagInventoryColumns = SMALL_BAG_INVENTORY_COLUMNS;
        } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 1) {
            bagInventoryRows = MEDIUM_BAG_INVENTORY_ROWS;
            bagInventoryColumns = MEDIUM_BAG_INVENTORY_COLUMNS;
        } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 2) {
            bagInventoryRows = LARGE_BAG_INVENTORY_ROWS;
            bagInventoryColumns = LARGE_BAG_INVENTORY_COLUMNS;
        }

        // Add the Storage Bag slots to the container
        for (int bagRowIndex = 0; bagRowIndex < bagInventoryRows; bagRowIndex++) {
            for (int bagColumnIndex = 0; bagColumnIndex < bagInventoryColumns; ++bagColumnIndex) {
                if (inventoryStorageBag.parentItemStack.getItemDamage() == 0) {
                    this.addSlotToContainer(new SlotStorageBag(this, inventoryStorageBag, entityPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 20 + bagRowIndex * 18));
                } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 1) {
                    this.addSlotToContainer(new SlotStorageBag(this, inventoryStorageBag, entityPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 20 + bagRowIndex * 18));
                } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 2) {
                    this.addSlotToContainer(new SlotStorageBag(this, inventoryStorageBag, entityPlayer, bagColumnIndex + bagRowIndex * bagInventoryColumns, 8 + bagColumnIndex * 18, 20 + bagRowIndex * 18));
                }
            }
        }

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; inventoryRowIndex++) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                if (inventoryStorageBag.parentItemStack.getItemDamage() == 0) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 51 + inventoryRowIndex * 18));
                } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 1) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 69 + inventoryRowIndex * 18));
                } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 2) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 87 + inventoryRowIndex * 18));
                }
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; actionBarSlotIndex++) {
            if (inventoryStorageBag.parentItemStack.getItemDamage() == 0) {
                if (!(entityPlayer.inventory.getStackInSlot(actionBarSlotIndex) == inventoryStorageBag.parentItemStack)) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 109));
                } else {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 109) {
                        public boolean canTakeStack (EntityPlayer player) {
                            return false;
                        }
                    });
                }
            } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 1) {
                if (!(entityPlayer.inventory.getStackInSlot(actionBarSlotIndex) == inventoryStorageBag.parentItemStack)) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 127));
                } else {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 127) {
                        public boolean canTakeStack (EntityPlayer player) {
                            return false;
                        }
                    });
                }
            } else if (inventoryStorageBag.parentItemStack.getItemDamage() == 2) {
                if (!(entityPlayer.inventory.getStackInSlot(actionBarSlotIndex) == inventoryStorageBag.parentItemStack)) {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 145));
                } else {
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 145) {
                        public boolean canTakeStack (EntityPlayer player) {
                            return false;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onContainerClosed (EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);
        if (!entityPlayer.worldObj.isRemote) {
            InventoryPlayer invPlayer = entityPlayer.inventory;
            for (ItemStack itemStack : invPlayer.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Names.NBT.STORAGE_BAG_GUI_OPEN);
                    }
                }
            }
            saveInventory(entityPlayer);
        }
    }

    @Override
    public boolean canInteractWith (EntityPlayer entityPlayer) {
        return true;
    }

    public boolean isItemStackParent (ItemStack itemStack) {
        if (NBTHelper.hasUUID(itemStack)) {
            UUID stackUUID = new UUID(itemStack.getTagCompound().getLong(Names.NBT.UUID_MOST_SIG), itemStack.getTagCompound().getLong(Names.NBT.UUID_LEAST_SIG));
            return inventoryStorageBag.matchesUUID(stackUUID);
        }
        return false;
    }

    @Override
    public ItemStack transferStackInSlot (EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();

            // Attempt to shift click something from the bag inventory into the player inventory
            if (slotIndex < bagInventoryRows * bagInventoryColumns) {
                if (!this.mergeItemStack(itemStack, bagInventoryRows * bagInventoryColumns, inventorySlots.size(), false)) {
                    return null;
                }
            }
            // Special case if we are dealing with an Storage Bag being shift clicked
            else if (itemStack.getItem() instanceof ItemStorageBag) {
                // Attempt to shift click a bag from the player inventory into the hot bar inventory
                if (slotIndex < (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS)) {
                    if (!this.mergeItemStack(itemStack, (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), inventorySlots.size(), false)) {
                        return null;
                    }
                }
                // Attempt to shift click a bag from the hot bar inventory into the player inventory
                else if (!this.mergeItemStack(itemStack, bagInventoryRows * bagInventoryColumns, (bagInventoryRows * bagInventoryColumns) + (PLAYER_INVENTORY_ROWS * PLAYER_INVENTORY_COLUMNS), false)) {
                    return null;
                }
            }
            // Attempt to shift click a non-Storage Bag into the bag inventory
            else if (!this.mergeItemStack(itemStack, 0, bagInventoryRows * bagInventoryColumns, false)) {
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

    public void saveInventory (EntityPlayer entityPlayer) {
        inventoryStorageBag.onGuiSaved(entityPlayer);
    }
}