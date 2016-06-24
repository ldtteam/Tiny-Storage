package com.smithsmodding.tinystorage.common.modules.factory;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.modules.ModuleFilter;

/**
 * Created by Tim on 24/06/2016.
 */
public class ModuleFactoryFilter implements IModuleFactory {

    @Override
    public ImmutableList<String> getBuildableModules() {
        return ImmutableList.of(References.Modules.ModuleNames.SMALL_FILTER_MODULE,
                References.Modules.ModuleNames.MEDIUM_FILTER_MODULE,
                References.Modules.ModuleNames.LARGE_FILTER_MODULE);
    }

    @Override
    public IModule buildModule(String uniqueId) throws ModuleConstructionException {
        switch (uniqueId) {
            case References.Modules.ModuleNames.SMALL_FILTER_MODULE:
                return new ModuleFilter(uniqueId, References.Modules.Filter.SMALL_SIZE);
            case References.Modules.ModuleNames.MEDIUM_FILTER_MODULE:
                return new ModuleFilter(uniqueId, References.Modules.Filter.MEDIUM_SIZE);
            case References.Modules.ModuleNames.LARGE_FILTER_MODULE:
                return new ModuleFilter(uniqueId, References.Modules.Filter.LARGE_SIZE);
            default:
                throw new ModuleConstructionException("The given module is unknown to this Factory.");
        }
    }
}
