
package models.strategies.mapstrat;

import java.util.ArrayList;
import java.util.Random;
import models.element.Wall;
import models.element.fighters.Fighter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe qui permet definir la strategie de placement des murs
 * @author Erwann
 */
public class WallStrat implements MapStrategy {

    private int wallNumber;
    
    private int nbdestructibles;
    
    /**
     * Constructeur des murs
     * @param pNumber
     *  le nombre de murs destructibles qu'on souhaite placer
     * @param nbdestructibles 
     *  le nombre de murs indestructibles qu'on souhaite placer
     */
    public WallStrat(int pNumber, int nbdestructibles) {
       this.wallNumber = pNumber; 
       this.nbdestructibles = nbdestructibles;
    }
    
    @Override
    public void generateItems(Object[][] map, ArrayList<Fighter> f) {
        Random r = new Random();
        Object[][] tmp = map;
        int x = 0,y = 0;
        int cpt = 0;
        // Murs Indestructibles
        while(cpt != this.wallNumber)
        {
           x = 0 + r.nextInt(map.length - 0);
           y = 0 + r.nextInt(map.length - 0);
           if(map[x][y] == null)
           {
               tmp[x][y] = new Wall(false); 
               cpt++;
           } 
        } 
        // Murs Destructibles
        cpt = 0;
        while(cpt != this.nbdestructibles)
        {
           x = 0 + r.nextInt(map.length - 0);
           y = 0 + r.nextInt(map.length - 0);
           if(map[x][y] == null)
           {
               tmp[x][y] = new Wall(true); 
               cpt++;
           } 
        } 
    }   
}