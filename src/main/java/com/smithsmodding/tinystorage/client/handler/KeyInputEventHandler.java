package com.smithsmodding.tinystorage.client.handler;

import com.smithsmodding.tinystorage.common.reference.Key;
import com.smithsmodding.tinystorage.network.message.MessageKeyPressed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.smithsmodding.tinystorage.client.settings.KeyBindings;
import com.smithsmodding.tinystorage.network.PacketHandler;
import com.smithsmodding.tinystorage.util.common.IKeyBound;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	private static Key getPressedKeybinding() {
		if (KeyBindings.changeMode.getIsKeyPressed()) {
			return Key.MODE;
		}
		return Key.UNKNOWN;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(KeyInputEvent event) {
		if (getPressedKeybinding() == Key.UNKNOWN) {
			return;
		}
		if (FMLClientHandler.instance().getClient().inGameHasFocus) {
			if (FMLClientHandler.instance().getClientPlayerEntity() != null) {
				EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
				if (entityPlayer.getCurrentEquippedItem() != null) {
					ItemStack currentlyEquippedItemStack = entityPlayer.getCurrentEquippedItem();
					if (currentlyEquippedItemStack.getItem() instanceof IKeyBound) {
						if (entityPlayer.worldObj.isRemote) {
							PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding()));
						} else {
							((IKeyBound) currentlyEquippedItemStack.getItem()).doKeyBindingAction(entityPlayer, currentlyEquippedItemStack, getPressedKeybinding());
						}
					}
				}
			}
		}
	}
}
