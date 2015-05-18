package com.timthebrick.tinystorage.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.timthebrick.tinystorage.reference.Key;
import com.timthebrick.tinystorage.util.IKeyBound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage> {

	private byte keyPressed;

	public MessageKeyPressed() {
	}

	public MessageKeyPressed(Key key) {
		if (key == Key.MODE) {
			this.keyPressed = (byte) Key.MODE.ordinal();
		} else {
			this.keyPressed = (byte) Key.UNKNOWN.ordinal();
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.keyPressed = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(keyPressed);
	}

	@Override
	public IMessage onMessage(MessageKeyPressed message, MessageContext ctx) {
		EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
		if (entityPlayer != null && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound) {
			if (message.keyPressed == Key.MODE.ordinal()) {
				((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), Key. MODE);
			}
		}
		return null;
	}

}
