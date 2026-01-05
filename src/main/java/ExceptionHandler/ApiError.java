package ExceptionHandler;

import java.time.LocalDateTime;

public class ApiError {

    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;

    public ApiError(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

