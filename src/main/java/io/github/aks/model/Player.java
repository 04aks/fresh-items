package io.github.aks.model;

public class Player {
    private final String ign;
    private final ItemBow bow;
    private final ItemPants pants;
    private final ItemSword sword;

    public Player(String ign){
        this.ign = ign;
        bow = new ItemBow();
        pants = new ItemPants();
        sword = new ItemSword();
    }

    public ItemBow getBow() {
        return bow;
    }

    public ItemPants getPants() {
        return pants;
    }

    public ItemSword getSword() {
        return sword;
    }

    public String getIgn() {
        return ign;
    }
}
