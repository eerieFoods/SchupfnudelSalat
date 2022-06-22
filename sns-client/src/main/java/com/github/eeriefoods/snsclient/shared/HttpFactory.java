package com.github.eeriefoods.snsclient.shared;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class HttpFactory {

    private static final HttpClient httpClient;

    private static final String[] JSON_HEADER = {"Content-Type", "application/json"};

    static {
        httpClient = HttpClient.newHttpClient();
    }
    private HttpFactory() {}

    /**
     * Sends a {@link HttpRequest} to {@code uri} with JSON Header, body and POST-Method.
     * Automatically calls {@code handleError} from {@link NotificationHandler} when statusCode is not 200
     * @param uri Server-URI
     * @param jsonBody Body in JSON-Format
     * @return {@link HttpResponse} of type {@code String}
     * @throws IOException from {@link HttpClient}'s {@code send}
     * @throws InterruptedException from {@link HttpClient}'s {@code send}
     */
    public static HttpResponse<String> sendPostJsonRequest(URI uri, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(uri)
                .headers(JSON_HEADER)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            NotificationHandler.handleHttpError(response);
            return response;
        }

        return response;
    }

    /**
     * Sends a {@link HttpRequest} to {@code uri} with GET-Method.
     * Automatically calls {@code handleError} from {@link NotificationHandler} when statusCode is not 200
     * @param uri Server-URI
     * @return {@link HttpResponse} of type {@code String}
     * @throws IOException from {@link HttpClient}'s {@code send}
     * @throws InterruptedException from {@link HttpClient}'s {@code send}
     */
    public static HttpResponse<String> sendGetRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(uri)
                .build();

        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            NotificationHandler.handleHttpError(response);
            return response;
        }

        return response;
    }

    /**
     * Sends a {@link HttpRequest} to {@code uri} with JSON Header, body and PUT-Method.
     * Automatically calls {@code handleError} from {@link NotificationHandler} when statusCode is not 200
     * @param uri Server-URI
     * @param jsonBody Body in JSON-Format
     * @return {@link HttpResponse} of type {@code String}
     * @throws IOException from {@link HttpClient}'s {@code send}
     * @throws InterruptedException from {@link HttpClient}'s {@code send}
     */
    public static HttpResponse<String> sendPutJsonRequest(URI uri, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(uri)
                .headers(JSON_HEADER)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            NotificationHandler.handleHttpError(response);
            return response;
        }

        return response;
    }

    /**
     * Sends a {@link HttpRequest} to {@code uri} with DELETE-Method.
     * Automatically calls {@code handleError} from {@link NotificationHandler} when statusCode is not 200
     * @param uri Server-URI
     * @return {@link HttpResponse} of type {@code String}
     * @throws IOException from {@link HttpClient}'s {@code send}
     * @throws InterruptedException from {@link HttpClient}'s {@code send}
     */
    public static HttpResponse<String> sendDeleteRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(uri)
                .headers(JSON_HEADER)
                .DELETE()
                .build();

        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            NotificationHandler.handleHttpError(response);
            return response;
        }

        return response;
    }

    /**
     * Create {@link HttpRequest} with JSON Header and PUT-Method
     * @param uri Server-URI
     * @param jsonBody Body in Json-Format
     * @return Parameterized {@link HttpRequest}
     */
    public static HttpRequest createPutJsonRequest(URI uri, String jsonBody) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .headers(JSON_HEADER)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
    }

}
