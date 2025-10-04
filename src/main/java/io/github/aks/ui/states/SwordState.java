package io.github.aks.ui.states;

import io.github.aks.ui.AppPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwordState extends State{
    public SwordState(AppPanel ap){
        super(ap);
        setType(CategoryType.SWORDS);
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
    }

    @Override
    public BufferedImage getIcon() {
        return ap.ui.swordImage;
    }
}
