package io.github.aks.service;

import io.github.aks.utils.ImageFetcher;
import java.awt.image.BufferedImage;

public class AvatarService {
    public BufferedImage fetchAvatar(String playerName){
        try{
            String baseLink = "https://mc-heads.net/avatar/%s/80";
            String link = String.format(baseLink, playerName);
            return ImageFetcher.getImageFromURL(link).get();
        }catch (Exception e){
            System.err.println("Couldn't fetch avatar image.");
            return null;
        }
    }
}
