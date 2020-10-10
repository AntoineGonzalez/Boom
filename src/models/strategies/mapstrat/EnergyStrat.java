package models.strategies.mapstrat;

import java.util.ArrayList;
import java.util.Random;
import models.element.EnergyShard;
import models.element.fighters.Fighter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Permet de definir la strategie de placement des pastilles d'energies
 * @author Erwann
 */
public class EnergyStrat implements MapStrategy {

    int energyNb;
    
    /**
     * Constructeur qui va definir le nombre de pastilles a placer
     * @param pNumber 
     */
    public EnergyStrat(int pNumber)
    {
        this.energyNb = pNumber;
    }
    //Ici on va placer des pastilles d'energies aleatoirement sur la map
    @Override
    public void generateItems(Object[][] map, ArrayList<Fighter> f) {
        Random r = new Random();
        int x = 0,y = 0;
        int cpt = 0;
        
        while(cpt != this.energyNb)
        {   
            x = 0 + r.nextInt(map.length - 0);
            y = 0 + r.nextInt(map.length - 0);
            if(map[x][y] == null)
            {
               map[x][y] = new EnergyShard(); 
               cpt++;
            } 
        }
    }
    
    
}
