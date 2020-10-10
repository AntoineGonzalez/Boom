

package models.strategies.mapstrat;

import java.util.ArrayList;
import models.element.fighters.Fighter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Permet de definir la strategie de placement des joueurs : dans les coins
 * @author Erwann
 */
public class FightersInCorner implements MapStrategy {

    public FightersInCorner() {
        
    }
    
    @Override
    public void generateItems(Object[][] map, ArrayList<Fighter> f) {
          /* On mets les fighter dans les coins de la maps*/

          //la taille du map
          int n,m;
          n = map.length; m = map[0].length;

          switch (f.size()) {
            //si il n'y a qu'un fighter dans la liste
            case 1: map[0][0] = f.get(0);
            break;

            //si on a deux fighter dans la liste
            case 2:
              map[0][0] = f.get(0);
              map[n-1][m-1] = f.get(1);
              break;

            //si on a 3 fighter dans la liste f
            case 3:
              map[0][0] = f.get(0);
              map[0][m-1] = f.get(1);
              map[n-1][m-1] = f.get(2);
              break;

            // si nous avons 4 combattants dans la liste
            case 4:
              map[0][0] = f.get(0);
              map[0][m-1] = f.get(1);
              map[n-1][0] = f.get(2);
              map[n-1][m-1] = f.get(3);
              break;
          }
    }

    
}
