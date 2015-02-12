package com.timthebrick.tinystorage.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class PlayerHelper {

	 /**
     * Sends a given message to a specific player
     */
	public static void sendChatMessage(EntityPlayer p, String message) {
		if (p != null && message != null && !message.isEmpty())
			p.addChatComponentMessage(new ChatComponentText(message));
	}

}
