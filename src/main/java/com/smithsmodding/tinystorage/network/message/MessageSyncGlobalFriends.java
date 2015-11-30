package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.core.TinyStorageLog;
import com.smithsmodding.tinystorage.common.entity.GlobalFriendsListRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.UUID;

public class MessageSyncGlobalFriends implements IMessage, IMessageHandler<MessageSyncGlobalFriends, IMessage> {

    HashMap<UUID, String> playerGlobalFriends;
    UUID playerID;

    public MessageSyncGlobalFriends() {
    }

    public MessageSyncGlobalFriends(UUID playerID) {
        this.playerGlobalFriends = GlobalFriendsListRegistry.getInstance().getPlayerGlobalFriends(playerID);
        this.playerID = playerID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerGlobalFriends = new HashMap<UUID, String>();
        int IDCountGlobal = buf.readInt();
        for (int IDNumber = 0; IDNumber < IDCountGlobal; IDNumber++) {
            UUID ID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
            String Name = ByteBufUtils.readUTF8String(buf);
            playerGlobalFriends.put(ID, Name);
        }
        playerID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerGlobalFriends.size());
        for (UUID ID : playerGlobalFriends.keySet()) {
            ByteBufUtils.writeUTF8String(buf, ID.toString());
            ByteBufUtils.writeUTF8String(buf, playerGlobalFriends.get(ID));
        }
        ByteBufUtils.writeUTF8String(buf, playerID.toString());
    }

    @Override
    public IMessage onMessage(MessageSyncGlobalFriends message, MessageContext ctx) {
        if (message.playerID.equals(FMLClientHandler.instance().getClientPlayerEntity().getUniqueID())) {
            TinyStorageLog.info("Syncing global friends list");
            TinyStorage.instance.playerGlobalFriends = message.playerGlobalFriends;
        }
        return null;
    }
}
