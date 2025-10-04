package io.github.aks.ui;

import io.github.aks.ui.states.CategoryType;
import io.github.aks.ui.states.State;
import io.github.aks.ui.util.Button;
import io.github.aks.utils.ResourceLoader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ui {
    private final Button backButton;
    public BufferedImage pitImage, swordImage, bowImage, pantsImage, trapImage, aucImage;
    private final AppPanel ap;
    public Color notHovered = new Color(0, 0, 0, 90);
    public Color hovered = new Color(0, 0, 0, 70);

    public Ui(AppPanel ap){
        this.ap = ap;
        backButton = new Button();
        loadImages();
    }

    public void draw(Graphics2D g2){
        if(ap.state == ap.home_state){
            ap.homePanel.draw(g2);
        }
        if(ap.state == ap.sword_state){
            ap.getCategoryStates().get(CategoryType.SWORDS).draw(g2);
        }
        if(ap.state == ap.bow_state){
            ap.getCategoryStates().get(CategoryType.BOWS).draw(g2);
        }
        if(ap.state == ap.pants_state){
            ap.getCategoryStates().get(CategoryType.PANTS).draw(g2);
        }
    }
    private void loadImages(){
        try{
            swordImage = ResourceLoader.getImage("/icons/sword.png");
            bowImage = ResourceLoader.getImage("/icons/bow.png");
            pantsImage = ResourceLoader.getImage("/icons/pants.png");
            pitImage = ResourceLoader.getImage("/pit.png");
            trapImage = ResourceLoader.getImage("/icons/trap.png");
            aucImage = ResourceLoader.getImage("/auschwitz.png");
        }catch(IOException e){
            System.err.println("Error while loading resources " + e);
        }
    }
    public int getXForCenteredText(Graphics2D g2, String text, int panelWidth){
        int length = getTextWidth(g2, text);
        return (panelWidth - length) / 2;
    }

    public int getTextWidth(Graphics2D g2, String text){
        return  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    public void drawCategoryHeader(Graphics2D g2, CategoryType current){
        g2.setColor(new Color(255,255,255,15));
        g2.fillRect(0,0,ap.getWIDTH(),60);
        int x = 5;
        int y = 5;
        g2.setColor(notHovered);
        g2.fillRoundRect(x,y,50,50, 15,15);
        backButton.setRectangle(new Rectangle(x,y,50,50));
        g2.drawImage(trapImage, x, y - 8, 50, 50, null);

        int otherX = ap.getWIDTH() - 70;
        for(CategoryType type : CategoryType.values()){
            if(type == current) continue;

            State state = ap.getCategoryStates().get(type);

            ap.getCurrentState().getStateButton().setRectangle(new Rectangle(otherX, y, 50, 50));
            g2.setColor(notHovered);
            if(ap.getCurrentState().getStateButton().isHovered()) g2.setColor(hovered);
            g2.fillRoundRect(otherX,y,50,50, 15,15);


            g2.drawImage(state.getIcon(), otherX + 3, y + 3, 44, 44, null);
            otherX -= 55;
        }
    }
}
