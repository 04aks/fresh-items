package io.github.aks.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {
    public static BufferedImage getImage(String path) throws IOException {
        InputStream is = ResourceLoader.class.getResourceAsStream(path);
        BufferedImage image = ImageIO.read(is);
        is.close();
        return image;
    }
}
