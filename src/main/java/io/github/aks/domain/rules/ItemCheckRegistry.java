package io.github.aks.domain.rules;

import io.github.aks.model.Player;

import java.util.Map;
import java.util.function.Consumer;

public class ItemCheckRegistry {
    private final Map<String, Consumer<Player>> check;
    public ItemCheckRegistry(){
        check = Map.of(
                "mystic sword", p -> p.getSword().addItem(),
                "mystic bow", p -> p.getBow().addItem(),
                "fresh pants", p -> p.getPants().addItem()
        );
    }

    public void applyChecks(Player player, String itemName){
        for(var entry : check.entrySet()){
            if(! itemName.contains(entry.getKey())) continue;
            entry.getValue().accept(player);
            break;
        }
    }
}
