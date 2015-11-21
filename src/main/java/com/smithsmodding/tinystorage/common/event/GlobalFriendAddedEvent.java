package com.smithsmodding.tinystorage.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public class GlobalFriendAddedEvent extends Event {

    public GlobalFriendAddedEvent(EntityPlayer player) {
        super();
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
