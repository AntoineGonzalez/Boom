
package models.element.explosives;

import models.element.fighters.Fighter;


/**
 *classe représentant un explosive dans notre modèle de jeu
 * @author antoine
 */
public abstract class Explosive{
    
    protected Fighter fighter;
    protected int cost;
    protected boolean visible;
    
    /**
     * Constructeur qui permet de créer une instance d'explosif en fonciton d'un fighter.
     * @param pFighter le combattant possédant la bombe.
     */
    public Explosive(Fighter pFighter){
        this.fighter=pFighter;
    }
    /**
     * Constructeur qui permet de créer une instance d'explosif en fonciton d'un fighter et de son cout en energie.
     * @param pFighter le combattant possédant la bombe.
     * @param pCost le cout en energie d'une bombe.
     */
    public Explosive(Fighter pFighter, int pCost){
        this(pFighter);
        this.cost=pCost;
        this.visible=false;
    }
    /**
     * renvoie le Fighter le propriétaire de la bombe
     * @return 
     */
    public Fighter getFighter() {
        return fighter;
    }
    
    /**
     * renvoie le cout d'une bombe.
     * @return 
     */
    public int getCost()
    {
        return this.cost;
    }
}

