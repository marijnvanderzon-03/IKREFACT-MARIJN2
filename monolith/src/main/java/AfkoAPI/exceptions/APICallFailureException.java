package AfkoAPI.exceptions;

public class APICallFailureException extends Exception {
    public APICallFailureException(String code, String errormessage) {
        super("\ncode: " + code + "\n\t| message: " + errormessage + "\n");
    }
}
