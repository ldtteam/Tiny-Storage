package com.timthebrick.tinystorage.util;

import com.timthebrick.tinystorage.core.TinyStorageLog;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.audio.PositionedSoundRecord;
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

	public static void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
		FMLClientHandler.instance().getClient().getSoundHandler().playSound(new PositionedSoundRecord(ResourceLocationHelper.getResourceLocation(soundName), volume, pitch, xCoord, yCoord, zCoord));
		TinyStorageLog.info("Play sound? HelperClass");
	}
	
	

}
