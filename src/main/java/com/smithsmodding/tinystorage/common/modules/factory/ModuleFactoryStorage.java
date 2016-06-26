package com.smithsmodding.tinystorage.common.modules.factory;

import com.google.common.collect.ImmutableList;
import com.smithsmodding.tinystorage.api.common.exception.ModuleConstructionException;
import com.smithsmodding.tinystorage.api.common.exception.ModuleStackContructionException;
import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;
import com.smithsmodding.tinystorage.api.reference.References;
import com.smithsmodding.tinystorage.common.modules.ModuleStorage;
import net.minecraft.item.ItemStack;

/**
 * Created by Tim on 23/06/2016.
 */
public class ModuleFactoryStorage implements IModuleFactory {

    @Override
    public ImmutableList<String> getBuildableModules() {
        return ImmutableList.of(References.Modules.ModuleNames.SMALL_STORAGE_MODULE,
                References.Modules.ModuleNames.MEDIUM_STORAGE_MODULE,
                References.Modules.ModuleNames.LARGE_STORAGE_MODULE);
    }

    @Override
    public IModule buildModule(String uniqueId) throws ModuleConstructionException {
        switch (uniqueId) {
            case References.Modules.ModuleNames.SMALL_STORAGE_MODULE:
                return new ModuleStorage(uniqueId, References.Modules.Storage.SMALL_SIZE);
            case References.Modules.ModuleNames.MEDIUM_STORAGE_MODULE:
                return new ModuleStorage(uniqueId, References.Modules.Storage.MEDIUM_SIZE);
            case References.Modules.ModuleNames.LARGE_STORAGE_MODULE:
                return new ModuleStorage(uniqueId, References.Modules.Storage.LARGE_SIZE);
            default:
                throw new ModuleConstructionException("The given module is unknown to this Factory.");
        }
    }
}
