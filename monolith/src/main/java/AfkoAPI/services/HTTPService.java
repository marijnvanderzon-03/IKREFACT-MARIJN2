package HTTPService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.APICallFailureException;
import exceptions.NoHTTPTokenException;
import exceptions.UnsuccesfullHTTPRequestException;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public class HTTPService<T> {
    private static final Logger logger = LogManager.getLogger(HTTPService.class);

    public final String API_URL = "http://localhost:8080/";
    private final OkHttpClient client = new OkHttpClient();
    private HTTPEndpoint endPoint;
    // save the generic type for use at runtime: https://stackoverflow.com/questions/3437897/how-do-i-get-a-class-instance-of-generic-type-t
    private Class<T> typeParameterClass;
    private static String token = null;

    protected HTTPService(HTTPEndpoint endPoint, Class<T> type) {
        this.endPoint = endPoint;
        this.typeParameterClass = type;
    }

    protected OkHttpClient getClient() { return client;}

    protected static void setToken(String t) {
        token = t;
    }

    protected void postRequest(T body) throws APICallFailureException, NoHTTPTokenException, UnsuccesfullHTTPRequestException {
        if (token == null)
            throw new NoHTTPTokenException();

        String url = API_URL + endPoint;

        Gson gson = new Gson();
        String json = gson.toJson(body, typeParameterClass);

        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        sendRequest(client.newCall(request));
    }


    protected T getRequest(HashMap<String, String> para) throws APICallFailureException, NoHTTPTokenException, UnsuccesfullHTTPRequestException {
        if (token == null)
            throw new NoHTTPTokenException();

        StringBuilder url = new StringBuilder(API_URL + endPoint.toString() + "?");

        for (String i : para.keySet()) {
            String sign = "&";
            url.append(sign);
            url.append(i).append("=").append(para.get(i));
        }

        Request request = new Request.Builder()
                .url(url.toString())
                .addHeader("Authorization", "Bearer " + token)
                .build();

        return sendRequest(client.newCall(request));
    }

    /** sends a Call request and parses the returned json into an object
     * @param call the call to get a response to
     * @return an object of type T parsed from returned json
     * @throws APICallFailureException
     */
    protected T sendRequest(Call call) throws APICallFailureException, UnsuccesfullHTTPRequestException {
        try (Response response = call.execute()) {
            if (!response.isSuccessful()){
                throw new UnsuccesfullHTTPRequestException(response.message() + "\n\tcode: " + response.code());
            }
            return getDataFromPayload(response.body().string());
        }
        catch (IOException e) {
            logger.warn(e);
            throw new UnsuccesfullHTTPRequestException(e.getMessage());
        }
    }

    /**
     * expects T to be an array of 2 objects
     * @param body
     * @throws APICallFailureException
     */
    protected void putRequest(T body) throws APICallFailureException, NoHTTPTokenException, UnsuccesfullHTTPRequestException {
        if (token == null)
            throw new NoHTTPTokenException();

        String url = "http://localhost:8080/" + endPoint;

        Gson gson = new Gson();
        String json = gson.toJson(body, typeParameterClass);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .put(requestBody)
                .url(url)
                .build();

        sendRequest(client.newCall(request));
    }

    protected void deleteRequest(T body) throws APICallFailureException, NoHTTPTokenException, UnsuccesfullHTTPRequestException {
        if (token == null)
            throw new NoHTTPTokenException();

        String url = "http://localhost:8080/" + endPoint;

        Gson gson = new Gson();
        String json = gson.toJson(body, typeParameterClass);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .delete(requestBody)
                .addHeader("Authorization", "Bearer " + token)
                .url(url)
                .build();

        sendRequest(client.newCall(request));
    }

    protected T getDataFromPayload(String payload) throws APICallFailureException {
        Gson gson = new GsonBuilder().create();
        JsonObject jobj = gson.fromJson(payload, JsonElement.class).getAsJsonObject();
        JsonElement data = jobj.get("data");
        JsonElement code = jobj.get("response");
        // the extra quotes are because code is still in json format or something idk
        if (!code.toString().equals("\"SUCCESS\"")) {
            throw new APICallFailureException(code.toString(), jobj.get("error").toString());
        }
        return gson.fromJson(data, typeParameterClass);
    }

}

