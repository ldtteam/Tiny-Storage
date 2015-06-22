package com.timthebrick.tinystorage.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import java.util.ArrayList;

public class SlotSpecificInput extends SlotTinyStorage {

    Class<? extends Item> filterItem;
    ArrayList<Class<? extends Item>> filterItemAdvanced;

    public SlotSpecificInput (IInventory inv, int id, int x, int y, Class<? extends Item> filterItem) {
        super(inv, id, x, y);
        this.filterItem = filterItem;
        filterItemAdvanced = null;
    }

    public SlotSpecificInput (IInventory inv, int id, int x, int y, Class<? extends Item>... filterItem) {
        super(inv, id, x, y);
        filterItem = null;
        Class<? extends Item> items[] = filterItem;
        for (int i = 0; i < items.length; i++) {
            filterItemAdvanced.add(items[i]);
        }
    }

    @Override
    public boolean isItemValid (ItemStack stack) {
        if (filterItemAdvanced == null && filterItem != null) {
            return filterItem.isAssignableFrom(stack.getItem().getClass());
        } else if (filterItem == null && filterItemAdvanced != null) {
            for (Class<? extends Item> item : filterItemAdvanced) {
                if (item.isAssignableFrom(stack.getItem().getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getSlotStackLimit () {
        return 64;
    }

}
