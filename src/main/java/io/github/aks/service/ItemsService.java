package io.github.aks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.aks.model.Player;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.JsonSerializer;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class ItemsService {
    private final String[] players;
    private final HttpTransport transport;
    private final JsonSerializer json;
    public ItemsService(String[] players, HttpTransport transport, JsonSerializer json){
        this.players = players;
        this.transport = transport;
        this.json = json;
    }

    public List<Player> findPlayersWithItems() throws JsonProcessingException {
        List<Player> playersList = new ArrayList<>();

        for(String playerName : players){

            String response = transport.get("/" + playerName, null);
            if(response.isEmpty()) continue;
            Player player = new Player(playerName);
            JsonNode inventoriesNode = json.jsonToNode(response).get("data").get("inventories");

            // main inventory
            filterItems(inventoriesNode, "main", player);
            // ender chest
            filterItems(inventoriesNode, "enderchest", player);
            // armor
            filterItems(inventoriesNode, "armor", player);

            playersList.add(player);
        }

        return playersList;
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
                case 283: player.getSword().addItem(); break;
                // bow
                case 261: player.getBow().addItem(); break;
                // pants
                case 300: player.getPants().addItem(); break;
            }
        }
    }
}
