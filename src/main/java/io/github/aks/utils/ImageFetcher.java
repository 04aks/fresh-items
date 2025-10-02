package io.github.aks.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageFetcher {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static Future<BufferedImage> getImageFromURL(String link){
        return executor.submit(() -> {
            BufferedImage image = null;
            try{
                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    image = ImageIO.read(conn.getInputStream());
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return image;
        });

    }
}
