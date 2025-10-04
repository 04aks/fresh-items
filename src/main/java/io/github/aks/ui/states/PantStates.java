package io.github.aks.ui.states;

import io.github.aks.ui.AppPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PantStates extends State{
    public PantStates(AppPanel ap) {
        super(ap);
        setType(CategoryType.PANTS);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public BufferedImage getIcon() {
        return ap.ui.pantsImage;
    }
}
