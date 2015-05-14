package com.timthebrick.tinystorage.handler;

import net.minecraft.item.ItemStack;

import com.timthebrick.tinystorage.item.ItemDebugTool;
import com.timthebrick.tinystorage.item.ItemDebugTool.OperationMode;
import com.timthebrick.tinystorage.item.ItemDebugTool.OperationModeOptions;
import com.timthebrick.tinystorage.util.NBTHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingEventHandler {
	
	@SubscribeEvent
	public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event){
		if (!event.player.worldObj.isRemote) {
			ItemStack stack = event.crafting;
			if(stack != null){
				if(stack.getItem() instanceof ItemDebugTool){
					ItemDebugTool debugTool = (ItemDebugTool) stack.getItem();
					OperationModeOptions operationMode = OperationModeOptions.RENDER_AREA;
					NBTHelper.setInteger(stack, "operationMode", operationMode.ordinal());
				}
			}
		}
	}

}
