package io.github.aks;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.aks.facade.FreshItems;
import io.github.aks.model.Player;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FreshItems freshItems = new FreshItems();
        try {
            List<Player> ps = freshItems.items(new String[]{"sbah", "ducttapedigger"}).findPlayersWithItems();
            System.out.println(ps.size());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}