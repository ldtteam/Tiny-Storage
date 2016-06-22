package com.smithsmodding.tinystorage.api.common.registries;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleRegistrationException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;

import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IModuleRegistry {

    /**
     * Method to register a new module Factory.
     *
     * @param factory The new module Factory.
     * @throws ModuleRegistrationException Thrown when there is an IModuleFactory that already can build a specific Module.
     */
    void registerModuleFactory(IModuleFactory factory) throws ModuleRegistrationException;

    /**
     * Method to get a new instance for a given ModuleId;
     *
     * @param Id The id of the module requested.
     * @return A new instance of a IModule that corresponds to the given ID.
     */
    IModule getModule(String Id);

    /**
     * Method to get all buildable Modules registered to this Factory.
     *
     * @return A list of all the buildable Modules.
     */
    ImmutableList<IModule> getAllBuildableModules();
}
