package io.github.aks.facade;

import io.github.aks.service.ItemsService;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;

public class FreshItems {
    private final String baseUrl;
    private final HttpTransport transport;
    private final JsonSerializer json;
    public FreshItems(){
        baseUrl = "https://pitpanda.rocks/api/players";
        transport = new HttpTransport(baseUrl);
        json = new JsonSerializer();
    }

    public ItemsService items(String[] players){
        return new ItemsService(players, transport, json);
    }


}
