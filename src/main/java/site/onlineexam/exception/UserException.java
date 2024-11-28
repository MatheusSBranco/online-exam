package site.onlineexam.exception;

/**
 * Custom exception to handle user-related errors.
 */
public class UserException extends RuntimeException {

    private final String additionalInfo;

    /**
     * Constructs a new UserException with the specified detail message and additional information.
     *
     * @param message       the detail message.
     * @param additionalInfo additional information about the exception.
     */
    public UserException(String message, String additionalInfo) {
        super(message);
        this.additionalInfo = additionalInfo;
    }

    /**
     * Returns the additional information related to the exception.
     *
     * @return additional information about the exception.
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }
}