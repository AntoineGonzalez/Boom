/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.element.explosives;

import models.element.fighters.Fighter;

/**
 * <b>Classe Bomb qui permet de reprèsenter une bombe dans notre modèle de jeu</b>
 * Une instance de bomb est représenter:
 * <ul>
 * <li>le Fighter auquelle elle appartient</li>
 * <li>un entier deffinissant le nombre de tour qu'il reste avant qu'elle explose.</li>
 * <li>le cout en energie qu'elle coute</li>
 * </ul>
 */
public class Bomb extends Explosive{
    
    private int nbTour;
    /**
     *  Constructeur retournant une instance de Bombe en fonction d'un combattant.
     * 
     * @param pFighter 
     */
    public Bomb(Fighter pFighter){
        super(pFighter);
        this.cost = 5;
        this.nbTour = 3;
    }
    /**
     * Constructeur retournant une instance de Bombe en fonction d'un combattant, d'un coup et d'un nombre de tour.
     * 
     * @param pFighter
     * @param pCost
     * @param nbtour 
     */
    public Bomb(Fighter pFighter, int pCost, int nbtour) {
        super(pFighter, pCost);
        this.nbTour = nbtour;
    }
    
    /**
     * Décremente le compteur de la bombe.
     */
    public void addTour()
    {
        this.nbTour--;
    }
    
    /**
     * retourne le compteur de la bombe.
     * @return 
     */
    public int getTour()
    {
        return this.nbTour;
    }
}
