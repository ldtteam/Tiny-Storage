package com.smithsmodding.tinystorage.api.common.exception;

/**
 * Author Orion (Created on: 22.06.2016)
 */
public class ModuleConstructionException extends RuntimeException {
    public ModuleConstructionException() {
        super();
    }

    public ModuleConstructionException(String s) {
        super(s);
    }

    public ModuleConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleConstructionException(Throwable cause) {
        super(cause);
    }
}
