package io.github.aks.model;

import io.github.aks.model.item.ItemBow;
import io.github.aks.model.item.ItemPants;
import io.github.aks.model.item.ItemSword;
import java.awt.image.BufferedImage;

public class Player {
    private final String ign;
    private BufferedImage avatar;
    private final ItemBow bow;
    private final ItemPants pants;
    private final ItemSword sword;

    public Player(String ign){
        this.ign = ign;
        bow = new ItemBow();
        pants = new ItemPants();
        sword = new ItemSword();
        avatar = null;
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

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }
}
