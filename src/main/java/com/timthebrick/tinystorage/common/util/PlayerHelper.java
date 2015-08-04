package com.timthebrick.tinystorage.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PlayerHelper {

	/**
	 * Sends a given message to a specific player
	 */
	public static void sendChatMessage(EntityPlayer p, String message) {
		if (p != null && message != null && !message.isEmpty()){
			p.addChatComponentMessage(new ChatComponentText(message));
		}
	}

	/**
	 * Sends a given unlocalised message to a specific player
	 */
	public static void sendChatMessage(EntityPlayer p, ChatComponentTranslation message) {
		if (p != null && message != null) {
			p.addChatComponentMessage(message);
		}
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
