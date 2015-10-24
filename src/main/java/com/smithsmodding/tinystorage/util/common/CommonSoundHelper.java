package com.smithsmodding.tinystorage.util.common;

import com.smithsmodding.tinystorage.network.message.MessageSoundEvent;
import net.minecraft.entity.player.EntityPlayer;

import com.smithsmodding.tinystorage.network.PacketHandler;

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
