package models.strategies.mapstrat;

import java.util.ArrayList;
import models.element.fighters.Fighter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Permet de definir la strategie de placement des joueurs : au milieu
 * @author Erwann
 */
public class FightersInMiddle implements MapStrategy {
    
    public FightersInMiddle() {
        
    }
    
    @Override
    public void generateItems(Object[][] map, ArrayList<Fighter> f) {
        //la taille du map

          int size = map.length;
          
          if(size >= 1)
          {
              map[(f.size()/2)][f.size()/2] = f.get(0);
          }
          if(size >= 2)
          {
              map[(f.size()/2)+1][(f.size()/2)+1] = f.get(1);
          }
          if(size >= 3)
          {
              map[(f.size()/2)+1][(f.size()/2)] = f.get(2);
          }
          if(size >= 4)
          {
              map[(f.size()/2)][(f.size()/2)+1] = f.get(3);
          }
    }
    
}