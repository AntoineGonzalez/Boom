/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import java.io.File;
import java.util.ArrayList;

/**
 * Permet de repertorier les couleurs disponibles pour un joueur et ses explosifs 
 * en fonction des resources disponibles
 * 
 */
public class AvailableColors {
    
    private File folder;
    
    private File[] files;
    
    /**
     * Constructeur de l'utilitaire de recuperation des couleurs disponibles
     */
    public AvailableColors()
    {
        this.folder = new File("resources/imgs/");
        this.files = folder.listFiles(); 
    }
    
    /**
     * Recupere les couleurs disponibles
     * @return 
     */
    public ArrayList<String> getColorsAvailable()
    {        
        ArrayList<String> colors = new ArrayList();
        for (int i = 0; i < this.files.length; i++) 
        {
          File current = files[i];
          if (current.isDirectory()) {
            String dirName = current.getName();
            colors.add(dirName);
          }
        }
        return colors;
    }
}
