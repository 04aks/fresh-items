package io.github.aks.ui;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    static final int WIDTH = 400;
    static final int HEIGHT = 250;
    int state = 0;
    final int homeState = 0;
    Ui ui;
    HomePanel homePanel;

    public AppPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ui = new Ui(this);
        homePanel = new HomePanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        ui.draw(g2);

        g2.dispose();
    }
}
