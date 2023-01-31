package co.zip.candidate.userapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd hh:mm:ss")
    private LocalDateTime localDateTime;
    private String message;

    public ErrorResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, LocalDateTime localDateTime, String message) {
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
