package co.zip.candidate.userapi.exceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String fieldName, String fieldValue, String errorMessage) {
        super(String.format("%s %s already exists: %s", fieldName, fieldValue, errorMessage));
    }
}
