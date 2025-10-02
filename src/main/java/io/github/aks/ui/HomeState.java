package io.github.aks.ui;

import io.github.aks.utils.ResourceLoader;
import io.github.aks.utils.StringUtils;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class HomeState {

    private BufferedImage pitImage, swordImage, bowImage, pantsImage;
    private final Button swordButton, bowButton, pantsButton, instructionButton;
    private final List<Button> buttons;
    private final AppPanel ap;
    public HomeState(AppPanel ap){
        this.ap = ap;
        swordButton = new Button();
        bowButton = new Button();
        pantsButton = new Button();
        instructionButton = new Button();
        buttons = List.of(swordButton, bowButton, pantsButton, instructionButton);
        try{
            swordImage = ResourceLoader.getImage("/icons/sword.png");
            bowImage = ResourceLoader.getImage("/icons/bow.png");
            pantsImage = ResourceLoader.getImage("/icons/pants.png");
            pitImage = ResourceLoader.getImage("/pit.png");
        }catch(IOException e){
            System.err.println("Error while loading resources " + e);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(pitImage, -300, -100, null);
        g2.setColor(new Color(0,0,0,130));
        g2.fillRect(0, 0, AppPanel.WIDTH, AppPanel.HEIGHT);


        int buttonSize = 100;
        int gap = 15;
        int containerWidth = buttonSize * 3 + gap * 2;
        int containerHeight = 100;

        int startX = (AppPanel.WIDTH - containerWidth) / 2;
        int startY = (AppPanel.HEIGHT - containerHeight) / 2;


        int x = startX;
        swordButton.setRectangle(new Rectangle(x, startY, 100, 100));
        x += gap + buttonSize;
        bowButton.setRectangle(new Rectangle(x, startY, 100, 100));
        x += gap + buttonSize;
        pantsButton.setRectangle(new Rectangle(x, startY, 100, 100));

        String instruction = "Click here to paste a list of IGNs separated by a comma";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        int instructionX = ap.ui.getXForCenteredText(g2, instruction, AppPanel.WIDTH);
        int instructionY = startY + buttonSize + 25;
        int instructionWidth = ap.ui.getTextWidth(g2, instruction);
        instructionButton.setRectangle(new Rectangle(instructionX - 5, instructionY - 20, instructionWidth + 10, 30));


        g2.setColor(ap.ui.notHovered);
        for(Button b : buttons){
            Rectangle r = b.getRectangle();
            if (b.isHovered()) {
                g2.setColor(ap.ui.hovered);
            }
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 20, 20);
            g2.setColor(ap.ui.notHovered);
        }

        //sword
        g2.drawImage(swordImage, swordButton.getRectangle().x + 10, startY + 10, 80, 80, null);
        //bow
        g2.drawImage(bowImage, bowButton.getRectangle().x + 10, startY + 10, 80, 80, null);
        //pants
        g2.drawImage(pantsImage, pantsButton.getRectangle().x, startY, 100, 100, null);


        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString(instruction, instructionX, instructionY);


    }


    public void homeStateMouseReleasedHandler(MouseEvent e){
        if(ap.state == ap.home_state){
            if(ap.homePanel.swordButton.getRectangle().contains(e.getX(), e.getY())){
                System.out.println("sword");
            }
            if(ap.homePanel.bowButton.getRectangle().contains(e.getX(), e.getY())){
                System.out.println("bow");
            }
            if(ap.homePanel.pantsButton.getRectangle().contains(e.getX(), e.getY())){
                System.out.println("pants");
            }
            if(ap.homePanel.instructionButton.getRectangle().contains(e.getX(), e.getY())){
                try {
                    ap.getFreshItems().playerManager().loadPlayers(StringUtils.getClipboard());
                } catch (IOException ex) {
                    throw new RuntimeException("Failed to access clipboard", ex);
                } catch (UnsupportedFlavorException ex) {
                    throw new RuntimeException("Clipboard does not contain plain text (expected comma-separated IGNs)", ex);
                }
            }
        }

    }
    public void homeStateMouseEnteredHandler(MouseEvent e){
        if(ap.state == ap.home_state){
            if(ap.homePanel.swordButton.getRectangle().contains(e.getX(), e.getY())){
                swordButton.setHovered(true);
            }
            if(ap.homePanel.bowButton.getRectangle().contains(e.getX(), e.getY())){
                bowButton.setHovered(true);
            }
            if(ap.homePanel.pantsButton.getRectangle().contains(e.getX(), e.getY())){
                pantsButton.setHovered(true);
            }
            if(ap.homePanel.instructionButton.getRectangle().contains(e.getX(), e.getY())){
                instructionButton.setHovered(true);
            }
        }

    }

    public void homeStateMouseExitedHandler(MouseEvent e){
        if(ap.state == ap.home_state){
            if(! ap.homePanel.swordButton.getRectangle().contains(e.getX(), e.getY())){
                swordButton.setHovered(false);
            }
            if(! ap.homePanel.bowButton.getRectangle().contains(e.getX(), e.getY())){
                bowButton.setHovered(false);
            }
            if(! ap.homePanel.pantsButton.getRectangle().contains(e.getX(), e.getY())){
                pantsButton.setHovered(false);
            }
            if(! ap.homePanel.instructionButton.getRectangle().contains(e.getX(), e.getY())){
                instructionButton.setHovered(false);
            }
        }

    }
}
