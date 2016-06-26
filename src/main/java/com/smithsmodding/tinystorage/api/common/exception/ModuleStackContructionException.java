package com.smithsmodding.tinystorage.api.common.exception;

/**
 * Author Orion (Created on: 26.06.2016)
 */
public class ModuleStackContructionException extends RuntimeException {
    public ModuleStackContructionException() {
        super();
    }

    public ModuleStackContructionException(String s) {
        super(s);
    }

    public ModuleStackContructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleStackContructionException(Throwable cause) {
        super(cause);
    }
}