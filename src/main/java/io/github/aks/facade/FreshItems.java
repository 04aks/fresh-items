package io.github.aks.facade;

import io.github.aks.manager.PlayerManager;
import io.github.aks.service.AvatarService;
import io.github.aks.service.InventoryService;
import io.github.aks.service.PlayerService;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;

public class FreshItems {
    private final PlayerManager playerManager;

    public FreshItems(){
        String baseUrl = "https://pitpanda.rocks/api/players";
        HttpTransport transport = new HttpTransport(baseUrl);
        JsonSerializer json = new JsonSerializer();
        AvatarService avatarService = new AvatarService();
        InventoryService inventoryService = new InventoryService(transport, json);
        PlayerService playerService = new PlayerService(avatarService, inventoryService);
        playerManager = new PlayerManager(playerService);
    }

    public PlayerManager playerManager(){
        return playerManager;
    }


}
