package co.zip.candidate.userapi.exceptions;

public class AccountPrerequisiteException extends RuntimeException {
    public AccountPrerequisiteException(String errorMessage) {
        super(errorMessage);
    }
}
