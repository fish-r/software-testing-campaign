package Exceptions;

public class InputException extends RuntimeException {
    public InputException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
