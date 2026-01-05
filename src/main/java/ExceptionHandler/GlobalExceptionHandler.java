package ExceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex) {
        return buildResponse(ex.getMessage(), "RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ApiError> handleSeatAlreadyBooked(SeatAlreadyBookedException ex) {
        return buildResponse(ex.getMessage(), "SEAT_ALREADY_BOOKED", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidBookingException.class)
    public ResponseEntity<ApiError> handleInvalidBooking(InvalidBookingException ex) {
        return buildResponse(ex.getMessage(), "INVALID_BOOKING", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
        return buildResponse(
                "Something went wrong",
                "INTERNAL_SERVER_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    private ResponseEntity<ApiError> buildResponse(String message, String code, HttpStatus status) {
        ApiError error = new ApiError(message, code, status.value());
        return new ResponseEntity<>(error, status);
    }



    static class ApiError {
        private String message;
        private String errorCode;
        private int status;

        public ApiError(String message, String errorCode, int status) {
            this.message = message;
            this.errorCode = errorCode;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public int getStatus() {
            return status;
        }
    }



    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class SeatAlreadyBookedException extends RuntimeException {
        public SeatAlreadyBookedException(String message) {
            super(message);
        }
    }

    public static class InvalidBookingException extends RuntimeException {
        public InvalidBookingException(String message) {
            super(message);
        }
    }
}
