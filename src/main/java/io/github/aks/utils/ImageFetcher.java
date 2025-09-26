package io.github.aks.utils;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageFetcher {
    public static void getImageFromURL(String playerName, String path){
        String link = "https://mc-heads.net/avatar/" + playerName + "/80";

        try{
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            InputStream is;
            OutputStream os;
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                is = conn.getInputStream();
                os = new FileOutputStream(path);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while((bytesRead = is.read(buffer)) != -1){
                    os.write(buffer, 0, bytesRead);
                }

                is.close();
                os.close();
            }else{
                System.out.println(conn.getResponseCode());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
