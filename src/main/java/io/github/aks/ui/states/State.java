package io.github.aks.ui.states;

import io.github.aks.ui.AppPanel;
import io.github.aks.ui.util.Button;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class State {
    private final Button stateButton;
    private CategoryType type;
    protected AppPanel ap;
    public State(AppPanel ap){
        this.ap = ap;
        stateButton = new Button();
    }
    public void draw(Graphics2D g2){
        g2.setColor(new Color(0,0,0,180));
        g2.drawImage(ap.ui.aucImage, -700, -650, null);
        g2.fillRect(0,0,ap.getWIDTH(), ap.getHEIGHT());
        ap.ui.drawCategoryHeader(g2, type);
    }
    public BufferedImage getIcon(){return null;}
    public Button getStateButton() {
        return stateButton;
    }
    public CategoryType getType() {
        return type;
    }
    public void setType(CategoryType type) {
        this.type = type;
    }
    public void mouseMovedIn(MouseEvent e){
        if(ap.state > 0 && stateButton.getRectangle().contains(e.getX(), e.getY())){ // not home state
            System.out.println("twin");
            stateButton.setHovered(true);
        }
    }
    public void mouseMovedOut(MouseEvent e){
        if(ap.state > 0 && ! stateButton.getRectangle().contains(e.getX(), e.getY())){
            System.out.println("untwin");
            stateButton.setHovered(false);
        }
    }

}
