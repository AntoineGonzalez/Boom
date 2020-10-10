package models.element.fighters;

import java.util.HashMap;
import models.strategies.fighterstrat.FighterStrategy;

/**
 *Classe representant un combattant de type Shooter dans notre modele  de jeu
 * Un combattant est caracteriser par 
 * <ul>
 * <li>Une energie (ses points de vie)</li>
 * <li>un nombre de bombe</li>
 * <li>un nombre de mine</li>
 * <li>un nombre de tirs</li>
 * <li>un nom</li>
 * <li>Un booleen qui décrit l'etat de son bouclier</li>
 * <li>Une couleur</li>
 * <li>Une Strategie decrivant son compportement de jeu</li>
 * </ul>
 * 
 * Un shooter est un combattant pour lequel on a augmenter le nombe de tir.
 */
public class Shooter extends Fighter {
    /**
     * Constructeur créant une instance de Shooter en fonction de tous ses parametres.
     * @param pEnergy nombre d'energie
     * @param pName son nom
     * @param pNb_shot son nombre de tir
     * @param pNb_bomb son nombre de bombe
     * @param pNb_mine son nombre de mine
     * @param pColor sa couleur
     * @param pStrat sa strategie
     */
    public Shooter(int pEnergy, String pName, int pNb_shot, int pNb_bomb, int pNb_mine, String pColor,FighterStrategy strat) {
        super(pEnergy, pName, pNb_shot, pNb_bomb, pNb_mine, pColor,strat);
    }
    
    /**
     * Constructeur creant une instance de shooter en fonction de son nom, sa couleur, ses parametre recuperé grace au parser de fichier et sa stratégie
     * 
     * @param pName son nom
     * @param pColor sa couleur
     * @param pParameter dictionnaire
     * @param strat sa strategie
     */
    public Shooter(String pName,String pColor, HashMap<String,Integer> pParameter,FighterStrategy strat){
        this(pName,pColor,strat);
        this.energy = pParameter.get("energy");
        this.nb_mine = pParameter.get("nb_mine");
        this.nb_bomb = pParameter.get("nb_bomb");
        this.nb_shot = pParameter.get("nb_shot");
    }
    
    /**
     * Constructeur creant une instance de Shooter en fonction de son nom, sa couleur et sa stratégie.
     * 
     * @param pName son nom
     * @param pColor sa couleur
     * @param strat sa strategie
     */
    public Shooter(String pName,String pColor,FighterStrategy strat){
        super(pName,pColor,strat);
        this.energy = 80;
        this.nb_bomb=2;
        this.nb_mine=2;
        this.shield=false;
        this.nb_shot=5;
    }
}
