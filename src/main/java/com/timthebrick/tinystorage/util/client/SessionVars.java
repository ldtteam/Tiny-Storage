package com.timthebrick.tinystorage.util.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SessionVars {

    private static List<UUID> recentFriends = new ArrayList<UUID>();

    public static void addRecentPlayer(UUID uuid) {
        if (recentFriends.contains(uuid)) {
            recentFriends.remove(uuid);
        }
        recentFriends.add(uuid);
    }

    public static List<UUID> getRecentFriends() {
        return recentFriends;
    }

}
