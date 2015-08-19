package com.timthebrick.tinystorage.util;

import net.minecraft.entity.player.EntityPlayer;

import com.timthebrick.tinystorage.network.PacketHandler;
import com.timthebrick.tinystorage.network.message.MessageSoundEvent;

import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonSoundHelper {

	public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch) {
		playSoundAt(entityPlayer, soundName, volume, pitch, 32d);
	}

	public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch, double range) {
		PacketHandler.INSTANCE.sendToAllAround(new MessageSoundEvent(entityPlayer, soundName, volume, pitch), new NetworkRegistry.TargetPoint(entityPlayer.worldObj.provider.dimensionId,
				entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, range));
	}

}
