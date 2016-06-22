package com.smithsmodding.tinystorage.common.creativetab;

import com.smithsmodding.tinystorage.api.reference.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 22/06/2016.
 */
public class TabTinyStorage {

    public static final CreativeTabs creativeTab = new CreativeTabs(References.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return new ItemStack(Items.APPLE).getItem();
        }
    };

}
