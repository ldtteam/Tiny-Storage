package com.smithsmodding.tinystorage.network.message;

import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.common.entity.GlobalFriendsListRegistry;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Orion
 * Created on 17.08.2015
 * 00:16
 * <p/>
 * Copyrighted according to Project specific license
 */
public class MessageSyncPlayerData implements IMessage, IMessageHandler<MessageSyncPlayerData, IMessage> {

    HashMap<UUID, String> playerList;
    HashMap<UUID, String> playerGlobalFriends;
    List<String> playerUUIDs;

    public MessageSyncPlayerData() {
    }

    public MessageSyncPlayerData(HashMap<UUID, String> playerList, List<String> playerUUIDs) {
        this.playerList = playerList;
        this.playerUUIDs = playerUUIDs;
    }

    public MessageSyncPlayerData(TinyStorage mod, UUID playerID) {
        this.playerList = mod.playerUUIDMap;
        this.playerGlobalFriends = GlobalFriendsListRegistry.getInstance().getPlayerGlobalFriends(playerID);
        this.playerUUIDs = mod.playerUUIDList;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerList.size());
        for (UUID ID : playerList.keySet()) {
            ByteBufUtils.writeUTF8String(buf, ID.toString());
            ByteBufUtils.writeUTF8String(buf, playerList.get(ID));
        }
        buf.writeInt(playerGlobalFriends.size());
        for (UUID ID : playerGlobalFriends.keySet()) {
            ByteBufUtils.writeUTF8String(buf, ID.toString());
            ByteBufUtils.writeUTF8String(buf, playerGlobalFriends.get(ID));
        }
        buf.writeInt(playerUUIDs.size());
        for (String string : playerUUIDs) {
            ByteBufUtils.writeUTF8String(buf, string);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        playerList = new HashMap<UUID, String>();
        int IDCount = buf.readInt();
        for (int IDNumber = 0; IDNumber < IDCount; IDNumber++) {
            UUID ID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
            String Name = ByteBufUtils.readUTF8String(buf);
            playerList.put(ID, Name);
        }
        playerGlobalFriends = new HashMap<UUID, String>();
        int IDCountGlobal = buf.readInt();
        for (int IDNumber = 0; IDNumber < IDCountGlobal; IDNumber++) {
            UUID ID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
            String Name = ByteBufUtils.readUTF8String(buf);
            playerGlobalFriends.put(ID, Name);
        }
        playerUUIDs = new ArrayList<String>();
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            String uuid = ByteBufUtils.readUTF8String(buf);
            playerUUIDs.add(uuid);
        }
    }

    @Override
    public IMessage onMessage(MessageSyncPlayerData message, MessageContext ctx) {
        TinyStorage.instance.playerUUIDList = message.playerUUIDs;
        TinyStorage.instance.playerGlobalFriends = message.playerGlobalFriends;
        TinyStorage.instance.playerUUIDMap = message.playerList;
        return null;
    }
}
