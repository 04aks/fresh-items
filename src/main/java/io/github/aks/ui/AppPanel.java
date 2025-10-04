package io.github.aks.ui;

import io.github.aks.facade.FreshItems;
import io.github.aks.ui.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

public class AppPanel extends JPanel implements MouseListener, MouseMotionListener {
    private final FreshItems freshItems;
    private int WIDTH = 500;
    private int HEIGHT = 300;
    static final Dimension HOME_SIZE = new Dimension(500, 300);
    static final Dimension CATEGORY_SIZE = new Dimension(500, 700);
    private final Map<CategoryType, State> categoryStates;
    public int state = 0;
    public final int home_state = 0;
    public final int sword_state = 1;
    public final int bow_state = 2;
    public final int pants_state = 3;
    public Ui ui;
    public HomeState homePanel;
    private final SwordState swordState;
    private final BowState bowState;
    private final PantStates pantStates;
    private State currentState;

    public AppPanel(FreshItems freshItems) {
        this.freshItems = freshItems;
        setPreferredSize(HOME_SIZE);
        addMouseListener(this);
        addMouseMotionListener(this);
        ui = new Ui(this);

        swordState = new SwordState(this);
        pantStates = new PantStates(this);
        bowState = new BowState(this);

        categoryStates = new HashMap<>();
        categoryStates.put(CategoryType.SWORDS, swordState);
        categoryStates.put(CategoryType.BOWS, bowState);
        categoryStates.put(CategoryType.PANTS, pantStates);
        homePanel = new HomeState(this);
    }

    public FreshItems getFreshItems() {
        return freshItems;
    }

    public Map<CategoryType, State> getCategoryStates() {
        return categoryStates;
    }

    public void expandToCategoryView(){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(frame == null) return;
        frame.setSize(CATEGORY_SIZE);
        frame.setLocationRelativeTo(null);
        setWIDTH(frame.getWidth());
        setHEIGHT(frame.getHeight());
    }
    public void shrinkToHomeView(){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if(frame == null) return;
        frame.setSize(HOME_SIZE);
        frame.setLocationRelativeTo(null);
        setWIDTH(frame.getHeight());
        setHEIGHT(frame.getHeight());
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
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

        if(currentState != null && currentState.getStateButton().getRectangle() != null) {
            currentState.mouseMovedIn(e);
            currentState.mouseMovedOut(e);
        }
        repaint();
    }
}
