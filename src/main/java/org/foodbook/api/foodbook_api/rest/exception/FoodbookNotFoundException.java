package org.foodbook.api.foodbook_api.rest.exception;

public class FoodbookNotFoundException extends RuntimeException {

    public FoodbookNotFoundException(String message) {
        super(message);
    }
}
