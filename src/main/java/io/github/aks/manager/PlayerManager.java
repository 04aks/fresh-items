package io.github.aks.manager;

import io.github.aks.model.Player;
import io.github.aks.service.PlayerService;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private final PlayerService playerService;
    private final Map<String, Player> playerMap = new HashMap<>();
    private boolean loading;
    public PlayerManager(PlayerService playerService){
        this.playerService = playerService;
    }

    public void loadPlayers(String playerNames){
        if(loading){
            System.out.println("Already loading players, please wait ...");
            return;
        }

        loading = true;
        try{
            List<Player> enriched = playerService.findPlayers(playerNames.split(","));
            enriched.forEach(p -> playerMap.put(p.getIgn(), p));
        }finally{
            loading = false;
        }
    }

    public Player getPlayer(String playerName){
        return playerMap.get(playerName);
    }

    public Collection<Player> getAllPlayers(){
        return playerMap.values();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
