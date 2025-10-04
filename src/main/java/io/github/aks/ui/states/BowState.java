package io.github.aks.ui.states;

import io.github.aks.ui.AppPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BowState extends State{
    public BowState(AppPanel ap) {
        super(ap);
        setType(CategoryType.BOWS);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public BufferedImage getIcon() {
        return ap.ui.bowImage;
    }
}
