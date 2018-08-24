package io.github.squarespheres.oslobikes.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class AbstractOsloBikeClient {


    private final String API_HOST = "https://oslobysykkel.no/api/v1/";
    private final HttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * Connect to the host API and fetch json information at specified path and map to java class
     *
     * @param clientId API client id
     * @param ApiPath  API path
     * @param classT   json mappable class that the json will be mapped to
     * @param <E>      json mappable class
     * @return class representation of the json
     * @throws IOException if connection or parsing failed
     */
    protected <E> E callHostAPI(String clientId, String ApiPath, Class<E> classT) throws IOException {

        HttpGet request = new HttpGet(API_HOST + ApiPath);
        request.addHeader("Client-Identifier", clientId);
        request.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException("Failed to fetch info from host : "
                    + response.getStatusLine().getStatusCode() + " - "
                    + response.getStatusLine().getReasonPhrase());
        }

        String json = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, classT);
    }
}
