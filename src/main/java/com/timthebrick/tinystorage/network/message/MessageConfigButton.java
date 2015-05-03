package com.timthebrick.tinystorage.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;
import com.timthebrick.tinystorage.tileentity.TileEntityTinyStorage;
import com.timthebrick.tinystorage.util.Utils;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigButton implements IMessage, IMessageHandler<MessageConfigButton, IMessage> {
	
	private EntityPlayer player;
	private ButtonSettings option;
	private float xCoord, yCoord, zCoord;
	private boolean rotationDirection;
	
	public MessageConfigButton() {
	}
	
	public MessageConfigButton(EntityPlayer player, ButtonSettings option, boolean rotationDirection, float xCoord, float yCoord, float zCoord) {
		this.player = player;
		this.option = option;
		this.rotationDirection = rotationDirection;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}
	
	@Override
	public void fromBytes(ByteBuf byteBuf) {
		this.option = ButtonSettings.values()[byteBuf.readInt()];
		this.rotationDirection = byteBuf.readBoolean();
		this.xCoord = byteBuf.readFloat();
		this.yCoord = byteBuf.readFloat();
		this.zCoord = byteBuf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf byteBuf) {
		byteBuf.writeInt(option.ordinal());
		byteBuf.writeBoolean(rotationDirection);
		byteBuf.writeFloat(xCoord);
		byteBuf.writeFloat(yCoord);
		byteBuf.writeFloat(zCoord);
	}
	
	@Override
	public IMessage onMessage(MessageConfigButton event, MessageContext context) {
		if(!FMLClientHandler.instance().getServer().getEntityWorld().isRemote){
			World world = FMLClientHandler.instance().getServer().getEntityWorld();
			TileEntity entity = world.getTileEntity((int)event.xCoord,(int) event.yCoord, (int)event.zCoord);
			if(entity != null && entity instanceof TileEntityTinyStorage){
				TileEntityTinyStorage te = (TileEntityTinyStorage) entity;
				if (event.option.ordinal() == ButtonSettings.AUTOMATED_SIDE_ACCESS.ordinal()){
					Enum<?> newState = Utils.rotateEnum( te.accessMode, event.rotationDirection, event.option.getPossibleValues() );
					te.accessMode = AccessMode.values()[newState.ordinal()];
					te.markDirty();
					world.markBlockForUpdate((int)event.xCoord,(int) event.yCoord, (int)event.zCoord);
				}
			}
		}
		return null;
	}

}
