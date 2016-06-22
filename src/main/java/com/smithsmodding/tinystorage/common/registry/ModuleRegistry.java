package com.smithsmodding.tinystorage.common.registry;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.TinyStorage;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.exception.ModuleRegistrationException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.common.registries.IModuleRegistry;
import scala.reflect.internal.Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class ModuleRegistry implements IModuleRegistry {

    private static ModuleRegistry INSTANCE = new ModuleRegistry();

    public static ModuleRegistry getInstance() {
        return INSTANCE;
    }

    LinkedHashMap<String, IModuleFactory> builders = new LinkedHashMap<>();
    ArrayList<IModule> baseModuleList = new ArrayList<>();

    private ModuleRegistry () {}

    @Override
    public void registerModuleFactory(IModuleFactory factory) throws ModuleRegistrationException {
        for(String moduleID : factory.getBuildableModules()) {
            if (builders.containsKey(moduleID))
                throw new ModuleRegistrationException("Failed to register a new ModuleFactory for module: " + moduleID + " it is already Registered.", builders.get(moduleID), builders.get(moduleID).buildModule(moduleID), moduleID);

            builders.put(moduleID, factory);

            try {
                IModule module = factory.buildModule(moduleID);

                if (!module.getUniqueID().equals(moduleID))
                    throw new ModuleRegistrationException("Pre-Build Module for ID: " + moduleID + " does not have to correct ID", factory, module, moduleID);

                baseModuleList.add(module);
            } catch (ModuleConstructionException moduleOnknownException) {
                throw new ModuleRegistrationException("The given factory did not know a module it was supposed to be able to build: " + moduleID, moduleOnknownException, factory, null, moduleID);
            } catch (Exception ex) {
                throw new ModuleRegistrationException("Failed to pre-build a Module for ID: " + moduleID, ex, factory, null, moduleID);
            }
        }
    }

    @Override
    public IModule getModule(String Id) {
        try {
            return builders.get(Id).buildModule(Id);
        } catch (Exception ex) {
            TinyStorage.getLogger().error(new Exception("Failed to build a Module for a given ID", ex));
        }

        return null;
    }

    @Override
    public ImmutableList<IModule> getAllBuildableModules() {
        return ImmutableList.copyOf(baseModuleList);
    }
}
