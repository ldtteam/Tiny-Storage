package com.smithsmodding.tinystorage.api.common.registries;

import com.smithsmodding.tinystorage.api.common.modules.IModule;

import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public interface IModuleRegistry {

    void registerNewModule(IModule module);

    IModule getModule(String uniqueID);

    LinkedHashMap<String, IModule> getAllRegisteredModules();

}
