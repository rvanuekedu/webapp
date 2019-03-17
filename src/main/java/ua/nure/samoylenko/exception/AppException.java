package ua.nure.samoylenko.exception;

public class AppException extends RuntimeException {

    AppException() {
        super();
    }

    public AppException(String text) {
        super(text);
    }

    AppException(String text, Throwable cause) {
        super(text, cause);
    }

}
