package com.smithsmodding.tinystorage.common.registry;

import com.smithsmodding.tinystorage.api.ITinyStorageAPI;
import com.smithsmodding.tinystorage.api.common.registries.IModuleRegistry;

import java.util.LinkedHashMap;

/**
 * Created by Tim on 22/06/2016.
 */
public class GeneralRegistry implements ITinyStorageAPI {

    private static GeneralRegistry instance = new GeneralRegistry();

    public LinkedHashMap<String, String> IMCRequests = new LinkedHashMap<>();
    private IModuleRegistry moduleRegistry = ModuleRegistry.getInstance();

    public static GeneralRegistry instance() {
        if (GeneralRegistry.instance == null)
            GeneralRegistry.instance = new GeneralRegistry();
        return GeneralRegistry.instance;
    }

    public void addIMCRequest(String method, String modname) {
        this.IMCRequests.put(method, modname);
    }

    @Override
    public IModuleRegistry getModuleRegistry() {
        return moduleRegistry;
    }
}
