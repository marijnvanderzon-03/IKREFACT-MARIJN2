package AfkoAPI.services;

public enum HTTPEndpoint {
    BLACKLIST("/blacklist"),
    CKECKBLACKLIST("/blacklist/check");


    private String value;

    private HTTPEndpoint(String val) {
        this.value = val;
    }

    public String toString() { return value; }
}
