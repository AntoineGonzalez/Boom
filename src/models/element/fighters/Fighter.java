
package models.element.fighters;

import java.util.HashMap;
import models.strategies.fighterstrat.FighterStrategy;

/**
 *Classe representant un combattant dans notre modele  de jeu
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
 */
public abstract class Fighter {
    protected int energy;      
    protected String name;     
    protected int nb_bomb;            
    protected boolean shield;           
    protected int nb_shot;            
    protected int nb_mine;  
    protected String color;
    protected FighterStrategy strat;
   
    /**
     * Constructeur créant une instance de fighter en fonction de tous ses parametres.
     * @param pEnergy nombre d'energie
     * @param pName son nom
     * @param pNb_shot son nombre de tir
     * @param pNb_bomb son nombre de bombe
     * @param pNb_mine son nombre de mine
     * @param pColor sa couleur
     * @param pStrat sa strategie
     */
    public Fighter(int pEnergy,String pName,int pNb_shot,int pNb_bomb,int pNb_mine,String pColor,FighterStrategy pStrat){
        this(pName,pColor,pStrat);
        this.energy = pEnergy;
        this.nb_bomb=pNb_bomb;
        this.nb_mine=pNb_mine;
        this.shield=false;
        this.nb_shot=pNb_shot;
    }
    
    /**
     * Constructeur creant une instance de fighter en fonction de son nom, sa couleur, ses parametre recuperé grace au parser de fichier et sa stratégie
     * 
     * @param pName son nom
     * @param pColor sa couleur
     * @param pParameter dictionnaire
     * @param strat sa strategie
     */
    public Fighter(String pName,String pColor, HashMap<String,Integer> pParameter,FighterStrategy strat){
        this(pName,pColor,strat);
        this.energy = pParameter.get("energy");
        this.nb_mine = pParameter.get("nb_mine");
        this.nb_bomb = pParameter.get("nb_bomb");
        this.nb_shot = pParameter.get("nb_shot");
    }
    
    /**
     * Constructeur creant une instance de fighter en fonction de son nom, sa couleur et sa stratégie.
     * 
     * @param pName son nom
     * @param pColor sa couleur
     * @param strat sa strategie
     */
    public Fighter(String pName,String pColor,FighterStrategy strat){
        this.name=pName;
        this.color = pColor;
        this.strat = strat;
    }
    
    /**
     * Recupere la strategie mise en place sur l'instance de de Fighter
     * @return 
     */
    public FighterStrategy getStrat()
    {
        return this.strat;
    }
    
    /**
     * Renvoie la couleur du Fighter
     * 
     * @return 
     */
    public String getColor()
    {
        return this.color;
    }
    
    /**
     * renvoie le nombre d'energoe du fighter
     * @return 
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * renvoie le nom du fighter
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     *renvoie le nombre de bombe du fighter 
     */
    public int getNb_bomb() {
        return nb_bomb;
    }

    /**
     * renvoie le nombre de mine du fighter
     * @return 
     */
    public int getNb_mine() {
        return nb_mine;
    }

    /**
     * renvoie l'etat du bouclier du fighter
     * @return 
     */
    public boolean hasShield() {
        return shield;
    }

    /**
     * renvoie le nombre de tir du fighter
     * @return 
     */
    public int getNb_shot() {
        return nb_shot;
    }

    /**
     * fixe l'energie du fighter à une certaine valeur
     * @param energy 
     */
    public void setEnergy(int energy) {
        if(!this.shield && energy < this.getEnergy())
        {
            this.energy = energy;
        }     
    }

    /**
     * place le bouclier d'un fighter à l'etat passer en parametre
     * @param shield 
     */
    public void setShield(boolean shield)
    {
        this.shield = shield;
    }
    
    /**
     * fixe le nom du fighter à l'aide  de la chaine de caractere passer en argument
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * fixe le nombre de bombe d'un fighter à une certaine valeur
     * @param nb_bomb 
     */
    public void setNb_bomb(int nb_bomb) {
        this.nb_bomb = nb_bomb;
    }

    /**
     * fixe le nombre de mine d'un fighter à une certaine valeur
     * @param nb_mine 
     */
    public void setNb_mine(int nb_mine) {
        this.nb_mine = nb_mine;
    }

    /**
     * fixe le nombre de tir d'un fighter à une certaine valeur
     * @param nb_shot 
     */
    public void setNb_shot(int nb_shot) {
        this.nb_shot = nb_shot;
    }
 
}