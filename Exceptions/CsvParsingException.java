package Exceptions;

public class CsvParsingException extends RuntimeException {
    public CsvParsingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
