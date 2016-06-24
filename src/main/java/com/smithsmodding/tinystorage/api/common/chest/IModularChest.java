package com.smithsmodding.tinystorage.api.common.chest;

import com.smithsmodding.smithscore.common.inventory.IItemStorage;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate3D;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IModularChest extends IItemStorage {
    /**
     * Method to get the world the Chest is in.
     *
     * @return The WorldOBJ of the Chest.
     */
    World getWorld();

    /**
     * Method to get the location of the Chest.
     *
     * @return The location of the Chest.
     */
    Coordinate3D getLocation();

    /**
     * Method to get the installed modules on the Chest.
     *
     * @return The installed modules
     */
    LinkedHashMap<String, IModule> getInstalledModules();

    /**
     * Method to install a new module.
     *
     * @param module The new module you want to install.
     */
    void installModule(IModule module);

    /**
     * @return The number of modules that can be installed in a given chest
     */
    int getModuleLimit();

    /**
     * @return The number of modules installed in the chest
     */
    int getModuleCount();

    boolean containsModule(String uniqueID);

    boolean containsModuleType(Class<? extends IModule> module);
}
