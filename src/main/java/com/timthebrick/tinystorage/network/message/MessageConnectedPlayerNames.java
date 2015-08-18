package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.core.TinyStorageLog;
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
public class MessageConnectedPlayerNames implements IMessage, IMessageHandler<MessageConnectedPlayerNames, IMessage> {

    HashMap<UUID, String> playerList;
    List<String> playerUUIDs;

    public MessageConnectedPlayerNames() {
    }

    public MessageConnectedPlayerNames(HashMap<UUID, String> playerList, List<String> playerUUIDs) {
        this.playerList = playerList;
        this.playerUUIDs = playerUUIDs;
    }

    public MessageConnectedPlayerNames(TinyStorage mod) {
        this.playerList = mod.playerUUIDMap;
        this.playerUUIDs = mod.playerUUIDList;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(playerList.size());
        for (UUID ID : playerList.keySet()) {
            ByteBufUtils.writeUTF8String(buf, ID.toString());
            ByteBufUtils.writeUTF8String(buf, playerList.get(ID));
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
        playerUUIDs = new ArrayList<String>();
        int count = buf.readInt();
        for (int i = 0; i < count; i++) {
            String uuid = ByteBufUtils.readUTF8String(buf);
            playerUUIDs.add(uuid);
        }
    }

    @Override
    public IMessage onMessage(MessageConnectedPlayerNames message, MessageContext ctx) {
        TinyStorage.instance.playerUUIDList = message.playerUUIDs;
        TinyStorage.instance.playerUUIDMap = message.playerList;
        return null;
    }
}
