package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.common.entity.GlobalFriendsListRegistry;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class MessageRemoveFriendGlobal implements IMessage, IMessageHandler<MessageRemoveFriendGlobal, IMessage> {

    private UUID playerUUID;
    private UUID owner;

    public MessageRemoveFriendGlobal() {
    }

    public MessageRemoveFriendGlobal(UUID owner, UUID id) {
        this.playerUUID = id;
        this.owner = owner;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        owner = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
        ByteBufUtils.writeUTF8String(buf, owner.toString());
    }

    @Override
    public IMessage onMessage(MessageRemoveFriendGlobal event, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            GlobalFriendsListRegistry.getInstance().removeGlobalFriend(event.owner, event.playerUUID);
        }
        return null;
    }
}
