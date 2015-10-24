package com.smithsmodding.tinystorage.common.inventory.implementations;

import com.smithsmodding.tinystorage.client.gui.widgets.GuiScrollBar;
import com.smithsmodding.tinystorage.client.gui.widgets.IGuiWidgetAdvanced;
import com.smithsmodding.tinystorage.common.inventory.ContainerTinyStorage;
import com.smithsmodding.tinystorage.common.inventory.slot.SlotTinyStorage;
import com.smithsmodding.tinystorage.common.tileentity.implementations.TileEntityImpossibleChest;
import com.smithsmodding.tinystorage.client.gui.widgets.IWidgetReceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerImpossibleChest extends ContainerTinyStorage implements IWidgetReceptor {

    private static final int INVENTORY_ROWS = 29;
    public static final int INVENTORY_COLUMNS = 9;
    public static final int INVENTORY_SIZE = INVENTORY_ROWS * INVENTORY_COLUMNS;
    private static final int DISPLAYABLE_ROW_SIZE = 6;

    private TileEntityImpossibleChest tileEntity;
    private InventoryPlayer inventoryPlayer;
    private int chestInventoryRows;
    private int chestInventoryColumns;

    public ContainerImpossibleChest(InventoryPlayer inventoryPlayer, TileEntityImpossibleChest tileEntity) {
        this.tileEntity = tileEntity;
        this.tileEntityTinyStorage = tileEntity;
        this.inventoryPlayer = inventoryPlayer;
        tileEntity.openInventory();
        chestInventoryRows = INVENTORY_ROWS;
        chestInventoryColumns = INVENTORY_COLUMNS;

        // Add chest slots to inventoryPlayer
        for (int chestRowIndex = 0; chestRowIndex < DISPLAYABLE_ROW_SIZE; chestRowIndex++) {
            for (int chestColumnIndex = 0; chestColumnIndex < chestInventoryColumns; chestColumnIndex++) {
                this.addSlotToContainer(new SlotTinyStorage(tileEntity, chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));
            }
        }

        // Add player inventoryPlayer to inventoryPlayer
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 140 + inventoryRowIndex * 18));
            }
        }

        // Add player hotbar to inventoryPlayer
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 198));
        }

        this.detectAndSendChanges();
        tileEntity.markDirty();
        tileEntity.getWorldObj().markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);
        this.detectAndSendChanges();
        tileEntity.markDirty();
        tileEntity.getWorldObj().markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
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
                if (!this.mergeItemStack(itemStack, chestInventoryRows * chestInventoryColumns, inventorySlots.size(), false)) {
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
        this.detectAndSendChanges();
        tileEntity.markDirty();
        tileEntity.getWorldObj().markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        return newItemStack;
    }

    @Override
    public ItemStack slotClick(int slotNum, int mouseButton, int modifier, EntityPlayer player) {
        this.detectAndSendChanges();
        tileEntity.markDirty();
        tileEntity.getWorldObj().markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        return super.slotClick(slotNum, mouseButton, modifier, player);
    }

    public void clearInventory(){
        this.inventorySlots.clear();
        this.inventoryItemStacks.clear();
    }

    @Override
    public void handleWidgetInteraction(IGuiWidgetAdvanced widget) {
        if (tileEntity.getWorldObj().isRemote) {
            if (widget instanceof GuiScrollBar) {
                GuiScrollBar scrollBar = (GuiScrollBar) widget;
                int startRow = scrollBar.getScrollPos() / 4;
                this.inventorySlots.clear();
                this.inventoryItemStacks.clear();

                // Add chest slots to inventoryPlayer
                for (int chestRowIndex = 0; chestRowIndex < DISPLAYABLE_ROW_SIZE; chestRowIndex++) {
                    for (int chestColumnIndex = 0; chestColumnIndex < chestInventoryColumns; chestColumnIndex++) {
                        Slot slot = new SlotTinyStorage(tileEntity, (startRow * INVENTORY_COLUMNS) + chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18);
                        this.addSlotToContainer(slot);
                        //TinyStorageLog.info("Slot added client side, ID: " + slot.slotNumber + " | Index: " + slot.getSlotIndex());
                    }
                }

                // Add player inventoryPlayer to inventoryPlayer
                for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
                    for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                        this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 140 + inventoryRowIndex * 18));
                    }
                }

                // Add player hotbar to inventoryPlayer
                for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
                    this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 198));
                }
            }
        } else {
            int startRow = tileEntity.scrollPos / 4;
            this.inventorySlots.clear();
            this.inventoryItemStacks.clear();

            // Add chest slots to inventoryPlayer
            for (int chestRowIndex = 0; chestRowIndex < DISPLAYABLE_ROW_SIZE; chestRowIndex++) {
                for (int chestColumnIndex = 0; chestColumnIndex < chestInventoryColumns; chestColumnIndex++) {
                    Slot slot = new SlotTinyStorage(tileEntity, (startRow * INVENTORY_COLUMNS) + chestColumnIndex + chestRowIndex * chestInventoryColumns, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18);
                    this.addSlotToContainer(slot);
                    //TinyStorageLog.info("Slot added server side, ID: " + slot.slotNumber + " | Index: " + slot.getSlotIndex());
                }
            }

            // Add player inventoryPlayer to inventoryPlayer
            for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
                for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                    this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 140 + inventoryRowIndex * 18));
                }
            }

            // Add player hotbar to inventoryPlayer
            for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 198));
            }
        }
        this.detectAndSendChanges();
        tileEntity.markDirty();
        tileEntity.getWorldObj().markBlockForUpdate(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
    }

    @Override
    public boolean canDragIntoSlot(Slot slot) {
        return false;
    }
}
