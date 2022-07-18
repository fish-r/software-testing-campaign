package Exceptions;

public class CsvComparisonException extends RuntimeException {
    public CsvComparisonException(String errorMessage) {
        super(errorMessage);
    }
}
