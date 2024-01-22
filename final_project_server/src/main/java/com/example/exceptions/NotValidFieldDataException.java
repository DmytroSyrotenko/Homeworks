package com.example.exceptions;

public class NotValidFieldDataException extends RuntimeException {
    public NotValidFieldDataException(String msg) {
        super(msg);
    }
}
