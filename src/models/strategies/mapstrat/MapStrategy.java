
package models.strategies.mapstrat;

import java.util.ArrayList;
import models.element.fighters.Fighter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe qui va servir a developper les strategies de placement sur la grille
 * @author Erwann
 */
public interface MapStrategy {
    public void generateItems(Object[][] map, ArrayList<Fighter> f);
}
