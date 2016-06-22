package com.smithsmodding.tinystorage.common.registry;

import com.smithsmodding.tinystorage.api.common.exception.ModuleRegistrationException;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.registries.IModuleRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class ModuleRegistry implements IModuleRegistry {

    private HashMap<String, IModule> registeredModules = new LinkedHashMap<>();

    @Override
    public void registerNewModule(IModule module) throws ModuleRegistrationException {
        if (registeredModules.containsKey(module.getUniqueID())) {
            throw new ModuleRegistrationException("A module with the ID: " + module.getUniqueID() + " has already been registered");
        } else {
            registeredModules.put(module.getUniqueID(), module);
        }
    }

    @Override
    public IModule getModule(String uniqueID) {
        if (registeredModules.containsKey(uniqueID)) {
            return registeredModules.get(uniqueID);
        }
        return null;
    }

    @Override
    public LinkedHashMap<String, IModule> getAllRegisteredModules() {
        return (LinkedHashMap) registeredModules.clone();
    }
}
