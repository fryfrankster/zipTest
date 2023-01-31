package co.zip.candidate.userapi.exceptions;

public class DuplicateFieldException extends RuntimeException{
    public DuplicateFieldException(String fieldName, String fieldValue, String errorMessage) {
        super(String.format("%s %s already exist: %s", fieldName, fieldValue, errorMessage));
    }
}
