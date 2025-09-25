package io.github.aks.ui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel(){
        setPreferredSize(new Dimension(AppPanel.WIDTH, AppPanel.HEIGHT));
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, AppPanel.WIDTH, AppPanel.HEIGHT);
    }
}
