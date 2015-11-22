package com.smithsmodding.tinystorage.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public class GlobalFriendAddedEvent extends Event {

    public UUID playerUUID;
    public String playerName;
    public String ownerIDSting;

    public GlobalFriendAddedEvent(String owner, UUID id, String playerName) {
        super();
        this.ownerIDSting = owner;
        this.playerUUID = id;
        this.playerName = playerName;
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
