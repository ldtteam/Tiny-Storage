package com.timthebrick.tinystorage.common.handler;

import com.timthebrick.tinystorage.common.item.ItemFriendSetter;
import com.timthebrick.tinystorage.common.item.crafting.RecipeStorageBags;
import com.timthebrick.tinystorage.util.common.IOwnable;
import com.timthebrick.tinystorage.util.common.ItemHelper;
import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.common.item.ItemDebugTool;
import com.timthebrick.tinystorage.common.item.ItemDebugTool.OperationModeSettings;
import com.timthebrick.tinystorage.util.common.NBTHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.crafting.CraftingManager;

public class CraftingEventHandler {

    public static void init() {
        CraftingManager.getInstance().getRecipeList().add(new RecipeStorageBags());
    }

    @SubscribeEvent
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof IOwnable) {
            ItemHelper.setOwner(event.crafting, event.player);
        }
        if (!event.player.worldObj.isRemote) {
            ItemStack stack = event.crafting;
            if (stack != null) {
                if (stack.getItem() instanceof ItemDebugTool) {
                    ItemDebugTool debugTool = (ItemDebugTool) stack.getItem();
                    ItemDebugTool.OperationModeSettings operationMode = ItemDebugTool.OperationModeSettings.RENDER_AREA;
                    NBTHelper.setInteger(stack, "operationMode", operationMode.ordinal());
                } else if (stack.getItem() instanceof ItemFriendSetter) {
                    ItemFriendSetter friendSetter = (ItemFriendSetter) stack.getItem();
                    ItemFriendSetter.OperationModeSettings operationMode = ItemFriendSetter.OperationModeSettings.READ_ONLY;
                    NBTHelper.setInteger(stack, "operationMode", operationMode.ordinal());
                }
            }
        }
    }

}
