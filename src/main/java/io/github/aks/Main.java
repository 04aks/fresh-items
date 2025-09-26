package io.github.aks;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.aks.facade.FreshItems;
import io.github.aks.model.Player;
import io.github.aks.ui.Window;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Window window = new Window();
        FreshItems freshItems = new FreshItems();
        List<Player> ps = freshItems.playerService().findPlayers(new String[]{"sbah", "kosher", "SupremeSteam"});
        ps.forEach(p -> {
            System.out.println(p.getIgn() + " -> avatar: " + (p.getAvatar() != null) + " -> pants: " + p.getPants().getItems());
        });

    }
}