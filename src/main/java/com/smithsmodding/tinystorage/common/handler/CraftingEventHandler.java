package com.smithsmodding.tinystorage.common.handler;

import com.smithsmodding.tinystorage.common.item.ItemDebugTool;
import com.smithsmodding.tinystorage.common.item.ItemFriendSetter;
import com.smithsmodding.tinystorage.common.item.crafting.RecipeStorageBags;
import com.smithsmodding.tinystorage.util.common.IOwnable;
import com.smithsmodding.tinystorage.util.common.ItemHelper;
import net.minecraft.item.ItemStack;

import com.smithsmodding.tinystorage.util.common.NBTHelper;

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
                    NBTHelper.setString(stack, "owner", event.player.getGameProfile().getId().toString() + event.player.getDisplayName());
                }
            }
        }
    }

}
