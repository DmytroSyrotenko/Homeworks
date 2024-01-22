package com.example.exceptions;

public class EntityUnexistsException extends RuntimeException {
    public EntityUnexistsException(String msg) {
        super(msg);
    }
}
