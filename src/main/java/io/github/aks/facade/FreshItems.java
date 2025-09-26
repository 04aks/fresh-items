package io.github.aks.facade;

import io.github.aks.service.AvatarService;
import io.github.aks.service.InventoryService;
import io.github.aks.service.PlayerService;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;

public class FreshItems {
    private final AvatarService avatarService;
    private final InventoryService inventoryService;
    public FreshItems(){
        String baseUrl = "https://pitpanda.rocks/api/players";
        HttpTransport transport = new HttpTransport(baseUrl);
        JsonSerializer json = new JsonSerializer();
        avatarService = new AvatarService();
        inventoryService = new InventoryService(transport, json);
    }

    public PlayerService playerService(){
        return new PlayerService(avatarService, inventoryService);
    }


}
