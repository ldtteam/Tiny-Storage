package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.common.entity.ExtendedPropertyGlobalFriends;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MessageSyncPlayerProperties implements IMessage, IMessageHandler<MessageSyncPlayerProperties, IMessage> {

    private NBTTagCompound data;

    public MessageSyncPlayerProperties() {
    }

    public MessageSyncPlayerProperties(EntityPlayer player) {
        data = new NBTTagCompound();
        ExtendedPropertyGlobalFriends.get(player).saveNBTData(data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
    }

    @Override
    public IMessage onMessage(MessageSyncPlayerProperties message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            ExtendedPropertyGlobalFriends.get(FMLClientHandler.instance().getClientPlayerEntity()).loadNBTData(message.data);
        }
        return null;
    }
}
