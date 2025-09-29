package io.github.aks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.aks.api.AuthProvider;
import io.github.aks.model.Player;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;

public class InventoryService {
    private final HttpTransport transport;
    private final JsonSerializer json;
    private final AuthProvider auth;
    public InventoryService(HttpTransport transport, JsonSerializer json, AuthProvider auth){
        this.transport = transport;
        this.json = json;
        this.auth = auth;
    }

    public void enrichInventory(Player player){
        String response = transport.get(player.getIgn(), auth.getAuthHeader());
        if(response.isEmpty()) return;

        JsonNode responseNode = null;
        boolean success;
        try {
            responseNode = json.jsonToNode(response);
            success = responseNode.get("success").asBoolean();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(! success) return;

        JsonNode statsNode = responseNode.get("player").get("stats");
        JsonNode pitNode = null;
        if(statsNode.has("Pit")){
            pitNode = statsNode.get("Pit");
        }
        System.out.println(pitNode != null ? pitNode.toString() : "null object");
//        System.out.println(statsNode.has("pit") ? statsNode.get("pit").asText() : "bruv");
//        // main inventory
//        filterItems(inventoriesNode, "main", player);
//        // ender chest
//        filterItems(inventoriesNode, "enderchest", player);
//        // armor
//        filterItems(inventoriesNode, "armor", player);
    }

    public void filterItems(JsonNode inventoriesNode, String inventory, Player player){
        for(JsonNode slot : inventoriesNode.get(inventory)){
            int itemID;
            try{
                itemID = slot.get("id").asInt();
            }catch (NullPointerException e){
                continue;
            }


            switch(itemID){
                // sword
                case 283 -> player.getSword().addItem();
                // bow
                case 261 -> player.getBow().addItem();
                // pants
                case 300 -> player.getPants().addItem();
            }
        }
    }
}
