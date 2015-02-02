package com.timthebrick.tinystorage.network.message;

import net.minecraft.tileentity.TileEntity;
import io.netty.buffer.ByteBuf;

import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityTinyStorage implements IMessage, IMessageHandler<MessageTileEntityTinyStorage, IMessage> {
	public int x, y, z;
	public byte orientation, state;
	public String customName, owner;

	public MessageTileEntityTinyStorage() {
	}

	public MessageTileEntityTinyStorage(TileEntityTinyStorage tileEntity) {
		this.x = tileEntity.xCoord;
		this.y = tileEntity.yCoord;
		this.z = tileEntity.zCoord;
		this.orientation = (byte) tileEntity.getOrientation().ordinal();
		this.state = (byte) tileEntity.getState();
		this.customName = tileEntity.getCustomName();
		this.owner = tileEntity.getOwner();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.orientation = buf.readByte();
		this.state = buf.readByte();
		int customNameLength = buf.readInt();
		this.customName = new String(buf.readBytes(customNameLength).array());
		int ownerLength = buf.readInt();
		this.owner = new String(buf.readBytes(ownerLength).array());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(orientation);
		buf.writeByte(state);
		buf.writeInt(customName.length());
		buf.writeBytes(customName.getBytes());
		buf.writeInt(owner.length());
		buf.writeBytes(owner.getBytes());
	}

	@Override
	public IMessage onMessage(MessageTileEntityTinyStorage message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityTinyStorage) {
			((TileEntityTinyStorage) tileEntity).setOrientation(message.orientation);
			((TileEntityTinyStorage) tileEntity).setState(message.state);
			((TileEntityTinyStorage) tileEntity).setCustomName(message.customName);
			((TileEntityTinyStorage) tileEntity).setOwner(message.owner);
		}

		return null;
	}

	@Override
	public String toString() {
		return String.format("MessageTileEntityTinyStorage - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x, y, z, orientation, state, customName, owner);
	}

}
