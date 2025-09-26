package io.github.aks.ui;

import io.github.aks.utils.ImageFetcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AppPanel extends JPanel implements MouseListener, MouseMotionListener {
    static final int WIDTH = 500;
    static final int HEIGHT = 300;
    int state = 0;
    final int home_state = 0;
    Ui ui;
    HomeState homePanel;

    public AppPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(this);
        addMouseMotionListener(this);
        ui = new Ui(this);
        homePanel = new HomeState(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ui.draw(g2);

        g2.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        homePanel.homeStateMouseReleasedHandler(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        homePanel.homeStateMouseEnteredHandler(e);
        homePanel.homeStateMouseExitedHandler(e);
        repaint();
    }
}
