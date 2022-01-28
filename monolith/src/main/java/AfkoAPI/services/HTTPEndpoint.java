package HTTPService;

public enum HTTPEndpoint {
    BLACKLIST("/blacklist");


    private String value;

    private HTTPEndpoint(String val) {
        this.value = val;
    }

    public String toString() { return value; }
}
