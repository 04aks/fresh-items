package io.github.aks.ui;

import java.awt.*;

public class Ui {
    private AppPanel ap;
    Color notHovered = new Color(0, 0, 0, 90);
    Color hovered = new Color(0, 0, 0, 70);

    public Ui(AppPanel ap){
        this.ap = ap;
    }

    public void draw(Graphics2D g2){
        if(ap.state == ap.home_state){
            ap.homePanel.draw(g2);
        }
    }

    public int getXForCenteredText(Graphics2D g2, String text, int panelWidth){
        int length = getTextWidth(g2, text);
        return (panelWidth - length) / 2;
    }

    public int getTextWidth(Graphics2D g2, String text){
        return  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

}
