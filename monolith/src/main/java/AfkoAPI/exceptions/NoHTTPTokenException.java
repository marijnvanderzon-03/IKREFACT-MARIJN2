package AfkoAPI.exceptions;

public class NoHTTPTokenException extends Exception {
    public NoHTTPTokenException() {
        super("Trying to do a HTTP request without a valid token");
    }
}
