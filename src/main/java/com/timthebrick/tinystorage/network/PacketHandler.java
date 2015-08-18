package com.timthebrick.tinystorage.network;

import com.timthebrick.tinystorage.common.core.TinyStorageLog;
import com.timthebrick.tinystorage.network.message.*;
import com.timthebrick.tinystorage.common.reference.References;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(References.MOD_ID.toLowerCase());

	public static void init() {
		TinyStorageLog.info("Initialising Packet Handler");
		INSTANCE.registerMessage(MessageSoundEvent.class, MessageSoundEvent.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageConfigButton.class, MessageConfigButton.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, 2, Side.SERVER);
        INSTANCE.registerMessage(MessageSpawnParticle.class, MessageSpawnParticle.class, 3, Side.CLIENT);
		INSTANCE.registerMessage(MessageScrollBar.class, MessageScrollBar.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageConnectedPlayerNames.class, MessageConnectedPlayerNames.class, 5, Side.CLIENT);
		INSTANCE.registerMessage(MessageAddFriend.class, MessageAddFriend.class, 6, Side.SERVER);
	}
}
