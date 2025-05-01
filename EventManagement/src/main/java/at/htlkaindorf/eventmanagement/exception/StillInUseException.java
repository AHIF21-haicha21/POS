package at.htlkaindorf.eventmanagement.exception;

public class StillInUseException extends RuntimeException {
    public StillInUseException(String message) {
        super(message);
    }
}
