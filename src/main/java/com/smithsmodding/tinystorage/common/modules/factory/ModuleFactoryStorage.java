package com.smithsmodding.tinystorage.common.modules.factory;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.reference.ModuleNames;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.modules.ModuleStorage;

/**
 * Created by Tim on 23/06/2016.
 */
public class ModuleFactoryStorage implements IModuleFactory {

    @Override
    public ImmutableList<String> getBuildableModules() {
        return ImmutableList.of(ModuleNames.smallStorageModule, ModuleNames.mediumStorageModule, ModuleNames.largeStorageModule);
    }

    @Override
    public IModule buildModule(String uniqueId) throws ModuleConstructionException {
        switch(uniqueId) {
            case ModuleNames.smallStorageModule:
                return new ModuleStorage(uniqueId, References.Modules.Storage.SMALLSIZE);
            case ModuleNames.mediumStorageModule:
                return new ModuleStorage(uniqueId, References.Modules.Storage.MEDIUMSIZE);
            case ModuleNames.largeStorageModule:
                return new ModuleStorage(uniqueId, References.Modules.Storage.LARGESIZE);
            default:
                throw new ModuleConstructionException("The given module is unknown to this Factory.");
        }
    }
}
