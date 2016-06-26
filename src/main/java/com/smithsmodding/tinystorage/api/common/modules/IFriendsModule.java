package com.smithsmodding.tinystorage.api.common.modules;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IFriendsModule extends IModule, IAccessModule {

    /**
     * Method to get the list of "friends" for this chest
     *
     * @return The list of friends associated with this chest
     */
    List<String> getFriendsList();

    /**
     * Method to get a list of players accessing the chest at a given time
     *
     * @return The Map of players accessing the chest
     */
    HashMap<UUID, String> getAccessedPlayers();

}
