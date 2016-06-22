package com.smithsmodding.tinystorage.common.registry;

import com.smithsmodding.tinystorage.api.ITinyStorageAPI;
import com.smithsmodding.tinystorage.api.common.registries.IModuleRegistry;

/**
 * Created by Tim on 22/06/2016.
 */
public class GeneralRegistry implements ITinyStorageAPI {

    private static IModuleRegistry registry = new ModuleRegistry();

    @Override
    public IModuleRegistry getModuleRegistry() {
        return registry;
    }
}
