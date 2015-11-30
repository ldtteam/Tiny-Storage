package com.smithsmodding.tinystorage.common.entity;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class GlobalFriendsListRegistry {

    private static GlobalFriendsListRegistry instance = null;
    private static File globalFriendsListDirectory;
    private static HashMap<UUID, HashMap<UUID, String>> playerFriendsList;

}
