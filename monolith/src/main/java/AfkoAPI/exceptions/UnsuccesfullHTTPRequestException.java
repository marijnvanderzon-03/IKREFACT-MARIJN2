package AfkoAPI.exceptions;

public class UnsuccesfullHTTPRequestException extends Exception {
    public UnsuccesfullHTTPRequestException(String message) {
        super("HTTP request failed response: " + message);
    }
}
