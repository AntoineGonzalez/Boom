/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Classe permetant de definir quelle image contient notre vue, par exemple
 * un mur, une pastille d'energie, une bombe ou encore une mine
 * @author Erwann
 */
public class CustomView extends FighterView {
    
    public CustomView(String path) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
           System.out.println("Impossible de charger l'image");
       }
    }  
}
