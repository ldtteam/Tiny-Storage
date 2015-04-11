package com.timthebrick.tinystorage.util;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.client.FMLClientHandler;

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
	}

	/**
	 * Check whether or not the player is classed as a fake player
	 * 
	 * @param player
	 *            The player to check
	 * @return
	 */
	public static boolean isPlayerFake(EntityPlayer player) {
		if (player.worldObj == null) {
			return true;
		}
		if (player.worldObj.isRemote) {
			return false;
		}
		if (player.getClass() == EntityPlayerMP.class) {
			return false;
		}
		return !MinecraftServer.getServer().getConfigurationManager().playerEntityList.contains(player);
	}

	/**
	 * Check whether or not the player is classed as a fake player
	 * 
	 * @param player
	 *            The player to check
	 * @return
	 */
	public static boolean isPlayerFake(EntityPlayerMP player) {
		if (player.getClass() != EntityPlayerMP.class) {
			return true;
		}
		if (player.playerNetServerHandler == null) {
			return true;
		}
		try {
			player.getPlayerIP().length();
			player.playerNetServerHandler.netManager.getSocketAddress().toString();
		} catch (Exception e) {
			return true;
		}
		return !MinecraftServer.getServer().getConfigurationManager().playerEntityList.contains(player);
	}

}
