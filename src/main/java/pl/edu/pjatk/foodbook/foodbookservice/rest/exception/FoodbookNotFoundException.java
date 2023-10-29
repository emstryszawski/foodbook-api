package pl.edu.pjatk.foodbook.foodbookservice.rest.exception;

public class FoodbookNotFoundException extends RuntimeException {

    public FoodbookNotFoundException() {

    }

    public FoodbookNotFoundException(String message) {
        super(message);
    }
}
