package io.github.aks.ui;

import java.awt.*;

public class Ui {
    private AppPanel ap;
    public Ui(AppPanel ap){
        this.ap = ap;
    }

    public void draw(Graphics2D g2){
        if(ap.state == ap.homeState){
            ap.homePanel.draw(g2);
        }
    }

}
