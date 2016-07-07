package com.smithsmodding.tinystorage.common.inventory;

import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentPlayerInventory;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import com.smithsmodding.smithscore.common.inventory.slot.SlotSmithsCore;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.tileentity.TileEntityTinyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by Tim on 26/06/2016.
 */
public class ContainerTinyStorage extends ContainerSmithsCore {

    private TileEntityTinyStorage tileEntity;

    public ContainerTinyStorage(TileEntityTinyStorage tileEntity, EntityPlayer playerMP) {
        super(References.TE, tileEntity, tileEntity, playerMP);
        this.tileEntity = tileEntity;
        generatePlayerInventory();
    }

    public TileEntityTinyStorage getTileEntity() {
        return tileEntity;
    }

    private void generatePlayerInventory() {
        for (int inventoryRowIndex = 0; inventoryRowIndex < PLAYER_INVENTORY_ROWS; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < PLAYER_INVENTORY_COLUMNS; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(getPlayerInventory(), inventoryColumnIndex + inventoryRowIndex * 9 + 9, 33 + inventoryColumnIndex * 18, 155 + inventoryRowIndex * 18));
            }
        }
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < PLAYER_INVENTORY_COLUMNS; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(getPlayerInventory(), actionBarSlotIndex, 33 + actionBarSlotIndex * 18, 214));
        }
    }

    private void generateCoreInventory() {
        for (int i = 0; i < tileEntity.getCoreModule().getSizeInventory(); i++) {
            this.addSlotToContainer(new SlotSmithsCore(tileEntity.getCoreModule(), i, (((ComponentPlayerInventory.WIDTH + 50) / 2) - (18 * tileEntity.getCoreModule().getSizeInventory() / 2)) + i * 18, 10));
        }
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onTabChanged(String newActiveTabID) {
        super.onTabChanged(newActiveTabID);
        inventorySlots.clear();
        inventoryItemStacks.clear();
        if (newActiveTabID.equals(References.GUIs.Tabs.CORE_MODULE)) {
            generateCoreInventory();
        }
        generatePlayerInventory();
    }
}
