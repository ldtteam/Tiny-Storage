package com.smithsmodding.tinystorage.api.common.exception;

/**
 * Author Orion (Created on: 26.06.2016)
 */
public class ModuleStackConstructionException extends RuntimeException {
    public ModuleStackConstructionException() {
        super();
    }

    public ModuleStackConstructionException(String s) {
        super(s);
    }

    public ModuleStackConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleStackConstructionException(Throwable cause) {
        super(cause);
    }
}