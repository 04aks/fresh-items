package io.github.aks.service;

import io.github.aks.utils.ImageFetcher;
import java.awt.image.BufferedImage;

public class AvatarService {
    public BufferedImage fetchAvatar(String playerName){
        try{
            return ImageFetcher.getImageFromURL(playerName).get();
        }catch (Exception e){
            System.err.println("Couldn't fetch avatar image.");
            return null;
        }
    }
}
