package com.smithsmodding.tinystorage.common.entity;

import com.smithsmodding.tinystorage.network.PacketHandler;
import com.smithsmodding.tinystorage.network.message.MessageSyncGlobalFriends;
import com.smithsmodding.tinystorage.util.common.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class GlobalFriendsListRegistry {

    private static GlobalFriendsListRegistry instance = null;
    private static File globalFriendsListDirectory;
    private static HashMap<UUID, HashMap<UUID, String>> playerFriendsListMap;

    private GlobalFriendsListRegistry() {
        globalFriendsListDirectory = new File(SerializationHelper.getPlayerDataDirectory(), "globalFriendsList");
        globalFriendsListDirectory.mkdirs();
        playerFriendsListMap = new HashMap<UUID, HashMap<UUID, String>>();
    }

    public static GlobalFriendsListRegistry getInstance() {
        if (instance == null) {
            instance = new GlobalFriendsListRegistry();
        }
        return instance;
    }

    public HashMap<UUID, String> getPlayerGlobalFriends(UUID playerUUID) {
        return playerFriendsListMap.get(playerUUID);
    }

    public void addGlobalFriend(UUID hostUUID, UUID friendUUID, String friendName) {
        playerFriendsListMap.get(hostUUID).put(friendUUID, friendName);
        PacketHandler.INSTANCE.sendToAll(new MessageSyncGlobalFriends(hostUUID));
    }

    public void removeGlobalFriend(UUID hostUUID, UUID friendUUID) {
        playerFriendsListMap.get(hostUUID).remove(friendUUID);
        PacketHandler.INSTANCE.sendToAll(new MessageSyncGlobalFriends(hostUUID));
    }

    public boolean isFriend(UUID playerA, UUID playerB) {
        return playerFriendsListMap.get(playerA).containsKey(playerB);
    }

    public void saveAll() {
        if (globalFriendsListDirectory != null) {
            for (UUID hostUUID : playerFriendsListMap.keySet()) {
                SerializationHelper.writePlayerFriendsListToFile(globalFriendsListDirectory, hostUUID.toString() + ".json", playerFriendsListMap.get(hostUUID));
            }
        }
    }

    public void loadAll() {
        if (globalFriendsListDirectory != null) {
            if (globalFriendsListDirectory.listFiles() != null) {
                for (File child : globalFriendsListDirectory.listFiles()) {
                    UUID hostUUID = UUID.fromString(child.getName().split("\\.")[0]);
                    HashMap<UUID, String> playerFriends = SerializationHelper.readPlayerFriendsListFromFile(child);
                    playerFriendsListMap.put(hostUUID, playerFriends);
                }
            }
        }
    }

    public void clear() {
        saveAll();
        this.instance = null;
    }

    public void savePlayerFriendsListToDisk(EntityPlayer entityPlayer) {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null) {
            savePlayerFriendsListToDisk(entityPlayer.getUniqueID());
        }
    }

    public void savePlayerFriendsListToDisk(UUID playerUUID) {
        if (playerUUID != null && globalFriendsListDirectory != null) {
            if (playerFriendsListMap.containsKey(playerUUID) && playerFriendsListMap.get(playerUUID) != null) {
                SerializationHelper.writePlayerFriendsListToFile(globalFriendsListDirectory, playerUUID.toString() + ".json", playerFriendsListMap.get(playerUUID));
            } else {
                loadPlayerFromDiskIfNeeded(playerUUID);
                SerializationHelper.writePlayerFriendsListToFile(globalFriendsListDirectory, playerUUID.toString() + ".json", playerFriendsListMap.get(playerUUID));
            }
        }
    }

    public void loadPlayerFromDiskIfNeeded(EntityPlayer entityPlayer) {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null) {
            loadPlayerFromDiskIfNeeded(entityPlayer.getUniqueID());
        }
    }

    private void loadPlayerFromDiskIfNeeded(UUID playerUUID) {
        if (playerUUID != null && playerFriendsListMap != null && !playerFriendsListMap.containsKey(playerUUID)) {
            HashMap<UUID, String> playerFriendsList = new HashMap<UUID, String>();
            File playerFriendsListFile = new File(globalFriendsListDirectory, playerUUID.toString() + ".json");
            if (playerFriendsListFile.exists() && playerFriendsListFile.isFile()) {
                playerFriendsList = SerializationHelper.readPlayerFriendsListFromFile(globalFriendsListDirectory, playerUUID.toString() + ".json");
            }
            playerFriendsListMap.put(playerUUID, playerFriendsList);
        }
    }

    public void unloadPlayer(EntityPlayer player) {
        //Probably do nothing here
    }
}
