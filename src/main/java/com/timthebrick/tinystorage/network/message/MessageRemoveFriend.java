package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.UUID;

public class MessageRemoveFriend implements IMessage, IMessageHandler<MessageRemoveFriend, IMessage> {

    private float xCoord, yCoord, zCoord;
    private UUID playerUUID;
    private String playerName;

    public MessageRemoveFriend(){
    }

    public MessageRemoveFriend(UUID id, String playerName, float xCoord, float yCoord, float zCoord) {
        this.playerUUID = id;
        this.playerName = playerName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        playerName = ByteBufUtils.readUTF8String(buf);
        this.xCoord = buf.readFloat();
        this.yCoord = buf.readFloat();
        this.zCoord = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
        ByteBufUtils.writeUTF8String(buf, playerName);
        buf.writeFloat(xCoord);
        buf.writeFloat(yCoord);
        buf.writeFloat(zCoord);
    }

    @Override
    public IMessage onMessage(MessageRemoveFriend event, MessageContext ctx) {
        if (!FMLClientHandler.instance().getServer().getEntityWorld().isRemote) {
            World world = FMLClientHandler.instance().getServer().getEntityWorld();
            TileEntity entity = world.getTileEntity((int) event.xCoord, (int) event.yCoord, (int) event.zCoord);
            if (entity != null && entity instanceof TileEntityTinyStorage) {
                TileEntityTinyStorage te = (TileEntityTinyStorage) entity;
                te.removeFriend(event.playerUUID, event.playerName);
                te.markDirty();
                world.markBlockForUpdate((int) event.xCoord, (int) event.yCoord, (int) event.zCoord);
            }
        }
        return null;
    }
}
