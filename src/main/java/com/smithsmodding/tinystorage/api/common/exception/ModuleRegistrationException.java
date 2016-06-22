package com.smithsmodding.tinystorage.api.common.exception;

/**
 * Created by Tim on 22/06/2016.
 */
public class ModuleRegistrationException extends RuntimeException {

    public ModuleRegistrationException() {
        super();
    }

    public ModuleRegistrationException(String s) {
        super(s);
    }

    public ModuleRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleRegistrationException(Throwable cause) {
        super(cause);
    }
}
