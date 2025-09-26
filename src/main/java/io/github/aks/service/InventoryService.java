package io.github.aks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.aks.model.Player;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;

public class InventoryService {
    private final HttpTransport transport;
    private final JsonSerializer json;
    public InventoryService(HttpTransport transport, JsonSerializer json){
        this.transport = transport;
        this.json = json;
    }

    public void enrichInventory(Player player){
        String response = transport.get("/" + player.getIgn(), null);
        if(response.isEmpty()) return;

        JsonNode inventoriesNode = null;
        try {
            inventoriesNode = json.jsonToNode(response).get("data").get("inventories");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // main inventory
        filterItems(inventoriesNode, "main", player);
        // ender chest
        filterItems(inventoriesNode, "enderchest", player);
        // armor
        filterItems(inventoriesNode, "armor", player);
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
