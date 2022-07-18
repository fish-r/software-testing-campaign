package Exceptions;

public class CsvParsingException extends RuntimeException {
    public CsvParsingException(String errorMessage) {
        super(errorMessage);
    }
}
