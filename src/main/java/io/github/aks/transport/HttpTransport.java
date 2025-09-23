package io.github.aks.transport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpTransport {
    private final String baseUrl;
    public HttpTransport(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public String get(String path, Map<String, String> headers){

        String url = baseUrl + path;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url));
        if(headers!=null) headers.forEach(requestBuilder::header);
        HttpRequest request = requestBuilder.build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
