package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.common.event.GlobalFriendAddedEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;

import java.util.UUID;

public class MessageAddFriendGlobal implements IMessage, IMessageHandler<MessageAddFriendGlobal, IMessage> {

    private UUID playerUUID;
    private String playerName;
    private String owner;

    public MessageAddFriendGlobal() {
    }

    public MessageAddFriendGlobal(String owner, UUID id, String playerName) {
        this.playerUUID = id;
        this.playerName = playerName;
        this.owner = owner;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        playerName = ByteBufUtils.readUTF8String(buf);
        owner = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
        ByteBufUtils.writeUTF8String(buf, playerName);
        ByteBufUtils.writeUTF8String(buf, owner);
    }

    @Override
    public IMessage onMessage(MessageAddFriendGlobal event, MessageContext ctx) {
        MinecraftForge.EVENT_BUS.post(new GlobalFriendAddedEvent(event.owner, event.playerUUID, event.playerName));
        return null;
    }
}
