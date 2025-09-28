package io.github.aks.service;

import io.github.aks.model.Player;
import java.util.Arrays;
import java.util.List;

public class PlayerService {
    private final AvatarService avatarService;
    private final InventoryService inventoryService;
    public PlayerService(AvatarService avatarService, InventoryService inventoryService){
        this.inventoryService = inventoryService;
        this.avatarService = avatarService;
    }

    public List<Player> findPlayers(String[] playerNames){
        return Arrays.stream(playerNames)
                .map(Player::new)
                .map(player -> {
                    // avatar
                    player.setAvatar(avatarService.fetchAvatar(player.getIgn()));
                    // inventory
                    try{
                        inventoryService.enrichInventory(player);
                    }catch(Exception e){
                        System.err.println("Something went wrong while fetching data: " + e);
                    }

                    return player;
                })
                .toList();
    }

}
