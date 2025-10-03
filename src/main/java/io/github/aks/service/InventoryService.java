package io.github.aks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.aks.api.AuthProvider;
import io.github.aks.domain.rules.ItemCheckRegistry;
import io.github.aks.model.Player;
import io.github.aks.transport.HttpTransport;
import io.github.aks.utils.Decoder;
import io.github.aks.utils.JsonSerializer;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;

import java.util.List;

public class InventoryService {
    private final HttpTransport transport;
    private final JsonSerializer json;
    private final AuthProvider auth;

    private final ItemCheckRegistry itemCheckRegistry;
    public InventoryService(HttpTransport transport, JsonSerializer json, AuthProvider auth){
        this.transport = transport;
        this.json = json;
        this.auth = auth;
        this.itemCheckRegistry = new ItemCheckRegistry();
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

        if(! statsNode.has("Pit")) return;
        JsonNode pitNode = statsNode.get("Pit");

        // ender chest
        JsonNode enderChestDataNode = pitNode.get("profile").get("inv_enderchest").get("data");
        // inventory
        JsonNode inventoryDataNode = pitNode.get("profile").get("inv_contents").get("data");
        // armor slots
        /*
            SOON COME
         */
        List<ListTag<CompoundTag>> allItemLists = List.of(
                getItemsListTag(enderChestDataNode),
                getItemsListTag(inventoryDataNode)
        );

        for(ListTag<CompoundTag> itemList : allItemLists){
            filterItems(itemList, player);
        }

    }

    public void filterItems(ListTag<CompoundTag> items, Player player){
        for(CompoundTag item : items){
            if(! item.containsKey("tag")) continue;
            CompoundTag tag = item.getCompoundTag("tag");

            if(! tag.containsKey("display")) continue;
            CompoundTag displayTag = tag.getCompoundTag("display");

            if(! displayTag.containsKey("Name")) continue;
            String itemName = displayTag.getString("Name")
                    .replaceAll("§.", "")
                            .toLowerCase();

            itemCheckRegistry.applyChecks(player, itemName);
        }

    }
    public ListTag<CompoundTag> getItemsListTag(JsonNode node){
        int[] invData = json.nodeToArray(node, int[].class);
        byte[] compressed = Decoder.intArrayToBytes(invData);
        CompoundTag root = Decoder.parsedNBT(Decoder.decodeToNBT(compressed));
        return root.getListTag("i").asCompoundTagList();
    }
}
