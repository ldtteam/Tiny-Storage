package com.smithsmodding.tinystorage.api.common.exception;

import com.smithsmodding.tinystorage.api.common.factory.IModuleFactory;
import com.smithsmodding.tinystorage.api.common.modules.IModule;

/**
 * Created by Tim on 22/06/2016.
 */
public class ModuleRegistrationException extends RuntimeException {

    private final IModuleFactory builder;
    private final IModule module;
    private final String moduleId;

    public ModuleRegistrationException(String message, IModuleFactory builder, IModule module, String moduleId) {
        super(message);

        this.builder = builder;
        this.module = module;
        this.moduleId = moduleId;
    }

    public ModuleRegistrationException(String message, Throwable exception, IModuleFactory builder, IModule module, String moduleId) {
        super(message, exception);

        this.builder = builder;
        this.module = module;
        this.moduleId = moduleId;
    }

    public IModuleFactory getBuilder() {
        return builder;
    }

    public IModule getModule() {
        return module;
    }

    public String getModuleId() {
        return moduleId;
    }
}
