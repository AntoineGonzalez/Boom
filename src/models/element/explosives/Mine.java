

package models.element.explosives;

import models.element.fighters.Fighter;


/**
 * <b>Classe Mine qui permet de reprèsenter une Mine dans notre modèle de jeu</b>
 * Une instance de Mine est représenter:
 * <ul>
 * <li>le Fighter auquelle elle appartient</li>
 * <li>le cout en energie qu'elle coute</li>
 * </ul>
 */
public class Mine extends Explosive{
    /**
     * Constructeur retournant une instance de Mine en fonction d'un combattant.
     * @param pFighter 
     */
    public Mine(Fighter pFighter){
        super(pFighter);
        
        this.cost=5;

    }
    /**
     *  Constructeur retournant une instance de Mine en fonction d'un combattant et d'un coup.
     * @param pFighter
     * @param pCost 
     */
    public Mine(Fighter pFighter, int pCost) {
        super(pFighter, pCost);
    }
}

