package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.common.tileentity.TileEntityTinyStorage;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.UUID;

public class MessagePlayerJoinGui implements IMessage, IMessageHandler<MessagePlayerJoinGui, IMessage> {

    private float x, y, z;
    private UUID playerID;
    private String playerDisplayName;

    public MessagePlayerJoinGui() {
    }

    public MessagePlayerJoinGui(EntityPlayer player, float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.playerID = player.getGameProfile().getId();
        this.playerDisplayName = player.getDisplayName();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
        this.playerID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.playerDisplayName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
        ByteBufUtils.writeUTF8String(buf, playerID.toString());
        ByteBufUtils.writeUTF8String(buf, playerDisplayName);
    }

    @Override
    public IMessage onMessage(MessagePlayerJoinGui event, MessageContext ctx) {
        if (!FMLClientHandler.instance().getServer().getEntityWorld().isRemote) {
            World world = FMLClientHandler.instance().getServer().getEntityWorld();
            TileEntity entity = world.getTileEntity((int) event.x, (int) event.y, (int) event.z);
            if (entity != null && entity instanceof TileEntityTinyStorage) {
                TileEntityTinyStorage te = (TileEntityTinyStorage) entity;
                te.playerOpenedGui(event.playerID, event.playerDisplayName);
            }
        }
        return null;
    }
}
