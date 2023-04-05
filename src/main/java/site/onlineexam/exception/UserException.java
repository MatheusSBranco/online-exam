package site.onlineexam.exception;

public class UserException extends RuntimeException {
    private final String data;

    public UserException(String message, String data) {
        super(message);
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
