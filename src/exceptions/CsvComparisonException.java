package exceptions;

public class CsvComparisonException extends RuntimeException {
    public CsvComparisonException(String errorMessage) {
        super(errorMessage);
    }
}
