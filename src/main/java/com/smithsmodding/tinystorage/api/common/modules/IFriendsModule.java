package com.smithsmodding.tinystorage.api.common.modules;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IFriendsModule extends IModule, IAccessModule {

    List<String> getFriendsList();

    HashMap<UUID, String> getAccessedPlayers();

}
