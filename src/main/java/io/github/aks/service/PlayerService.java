package io.github.aks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.aks.model.Player;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.ImageFetcher;
import io.github.aks.utils.JsonSerializer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

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
                    player.setAvatar(avatarService.fetchAvatar(player.getIgn()));

                    /*
                    try catch block because an exception was thrown before
                    lost the logs, donnu what it was exactly
                     */
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
