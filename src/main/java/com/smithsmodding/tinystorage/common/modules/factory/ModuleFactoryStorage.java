package com.smithsmodding.tinystorage.common.modules.factory;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;

/**
 * Created by Tim on 23/06/2016.
 */
public class ModuleFactoryStorage implements IModuleFactory {

    @Override
    public ImmutableList<String> getBuildableModules() {
        return null;
    }

    @Override
    public IModule buildModule(String uniqueId) throws ModuleConstructionException {
        return null;
    }
}
