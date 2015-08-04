package com.timthebrick.tinystorage.common.inventory.slot;

import com.timthebrick.tinystorage.common.inventory.implementations.ContainerStorageBag;
import com.timthebrick.tinystorage.common.item.ItemStorageBag;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotStorageBag extends SlotTinyStorage {
    private final EntityPlayer entityPlayer;
    private ContainerStorageBag containerAlchemicalBag;

    public SlotStorageBag (ContainerStorageBag containerAlchemicalBag, IInventory inventory, EntityPlayer entityPlayer, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.entityPlayer = entityPlayer;
        this.containerAlchemicalBag = containerAlchemicalBag;
    }

    @Override
    public void onSlotChanged () {
        super.onSlotChanged();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            containerAlchemicalBag.saveInventory(entityPlayer);
        }
    }

    @Override
    public boolean isItemValid (ItemStack itemStack) {
        return !(itemStack.getItem() instanceof ItemStorageBag);
    }
}
