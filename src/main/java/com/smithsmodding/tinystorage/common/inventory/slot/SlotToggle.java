package com.smithsmodding.tinystorage.common.inventory.slot;

import net.minecraft.inventory.IInventory;

public class SlotToggle extends SlotTinyStorage {

    public boolean isVisible;

    public SlotToggle(IInventory inv, int id, int x, int y, boolean visible){
        super(inv, id, x, y);
        this.isVisible = visible;
    }
}
