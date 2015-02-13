package com.timthebrick.tinystorage.network.message;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.core.TinyStorageLog;
import com.timthebrick.tinystorage.util.Settings;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSoundEvent implements IMessage, IMessageHandler<MessageSoundEvent, IMessage> {

	private long mostSigUUID, leastSigUUID;
	private String soundName;
	private float xCoord, yCoord, zCoord;
	private float volume, pitch;

	public MessageSoundEvent() {

	}

	public MessageSoundEvent(EntityPlayer entityPlayer, String soundName, float volume, float pitch) {
		this.mostSigUUID = entityPlayer.getUniqueID().getMostSignificantBits();
		this.leastSigUUID = entityPlayer.getUniqueID().getLeastSignificantBits();
		this.soundName = soundName;
		this.xCoord = (float) entityPlayer.posX;
		this.yCoord = (float) entityPlayer.posY;
		this.zCoord = (float) entityPlayer.posZ;
		this.volume = volume;
		this.pitch = pitch;
	}

	public MessageSoundEvent(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
		this.mostSigUUID = 0;
		this.leastSigUUID = 0;
		this.soundName = soundName;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.volume = volume;
		this.pitch = pitch;
	}

	@Override
	public void fromBytes(ByteBuf byteBuf) {
		this.mostSigUUID = byteBuf.readLong();
		this.leastSigUUID = byteBuf.readLong();
		int soundNameLength = byteBuf.readInt();
		this.soundName = new String(byteBuf.readBytes(soundNameLength).array());
		this.xCoord = byteBuf.readFloat();
		this.yCoord = byteBuf.readFloat();
		this.zCoord = byteBuf.readFloat();
		this.volume = byteBuf.readFloat();
		this.pitch = byteBuf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf byteBuf) {
		byteBuf.writeLong(mostSigUUID);
		byteBuf.writeLong(leastSigUUID);
		byteBuf.writeInt(soundName.length());
		byteBuf.writeBytes(soundName.getBytes());
		byteBuf.writeFloat(xCoord);
		byteBuf.writeFloat(yCoord);
		byteBuf.writeFloat(zCoord);
		byteBuf.writeFloat(volume);
		byteBuf.writeFloat(pitch);
	}

	@Override
	public IMessage onMessage(MessageSoundEvent event, MessageContext context) {
		UUID originUUID = new UUID(event.mostSigUUID, event.leastSigUUID);
		if (Settings.Sounds.soundMode.equalsIgnoreCase("All")) {
			TinyStorage.proxy.playSound(event.soundName, event.xCoord, event.yCoord, event.zCoord, event.volume, event.pitch);
		} else if (Settings.Sounds.soundMode.equalsIgnoreCase("Self")) {
			if (FMLClientHandler.instance().getClient().thePlayer.getUniqueID().equals(originUUID)) {
				TinyStorage.proxy.playSound(event.soundName, event.xCoord, event.yCoord, event.zCoord, event.volume, event.pitch);
			}
		}
		return null;
	}

}
