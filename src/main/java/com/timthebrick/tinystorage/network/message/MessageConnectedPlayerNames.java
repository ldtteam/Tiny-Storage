package com.timthebrick.tinystorage.network.message;

import com.timthebrick.tinystorage.TinyStorage;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
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

    public MessageConnectedPlayerNames() {
    }

    public MessageConnectedPlayerNames(HashMap<UUID, String> playerList) {
        this.playerList = playerList;
    }

    public MessageConnectedPlayerNames(TinyStorage mod) {
        this.playerList = mod.playerList;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        buf.writeInt(playerList.size());
        for (UUID ID : playerList.keySet()) {
            ByteBufUtils.writeUTF8String(buf, ID.toString());
            ByteBufUtils.writeUTF8String(buf, playerList.get(ID));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        playerList = new HashMap<UUID, String>();
        int IDCount = buf.readInt();
        for (int IDNumber = 0; IDNumber < IDCount; IDNumber++) {
            UUID ID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
            String Name = ByteBufUtils.readUTF8String(buf);
            playerList.put(ID, Name);
        }
    }

    @Override
    public IMessage onMessage(MessageConnectedPlayerNames message, MessageContext ctx) {
        TinyStorage.instance.playerList = message.playerList;
        return null;
    }
}
