package io.github.aks.ui.util;

import java.awt.*;

public class Button {
    private Rectangle rectangle;
    private boolean hovered;

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
