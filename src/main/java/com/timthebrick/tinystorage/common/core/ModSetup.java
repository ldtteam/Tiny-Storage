package com.timthebrick.tinystorage.common.core;

import com.timthebrick.tinystorage.common.init.Registration;
import com.timthebrick.tinystorage.common.reference.References;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = References.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final ItemGroup ITEM_GROUP = new ItemGroup("tinystorage") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.TINY_CHEST_STONE_SMALL.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {

    }

}
