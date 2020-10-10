/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.factories;
import java.util.HashMap;
import models.element.fighters.Shooter;
import models.element.fighters.Gardian;
import models.element.fighters.Bomber;
import models.element.fighters.Fighter;
import models.strategies.fighterstrat.FighterStrategy;

/**
 * Classe qui permet de créer un FghterFactory dans le cadre du pattern Factory.
 * C'est cette classe qui est charger d'instancier nos différents Fighter.
 * @author antoine
 */
public class FighterFactory {
    public FighterFactory(){
        
    }
    /**
     * Methode qui retourne un Fighter, l'instance créer sera une instance de Bomber , Shooter 
     * ou Gardian en fonction du parametre type. L'instance est obtenue grace à l'ensemeble des parametres.
     * @param type caractere representant le type d'instance que l'on souhaite recuperer.
     * @param pEnergy energie du fighter
     * @param pName nom du fighter
     * @param pNb_shot nombre de tir du fighter
     * @param pNb_bomb nombre de bombe du fighter
     * @param pNb_mine nombre de mine du fighter
     * @param pColor couleur du fighter
     * @param strat strategie assigné au fighter
     * @return 
     */
    public Fighter createFighter(char type, int pEnergy ,String pName,int pNb_shot,int pNb_bomb,int pNb_mine,String pColor,FighterStrategy strat){
        switch (type){
            case 'G' : return new Gardian(pEnergy,pName,pNb_shot,pNb_bomb,pNb_mine,pColor,strat);
            case 'S' : return new Shooter(pEnergy,pName,pNb_shot,pNb_bomb,pNb_mine,pColor,strat);
            case 'B' : return new Bomber(pEnergy,pName,pNb_shot,pNb_bomb,pNb_mine,pColor,strat);
        }
        return null;
    }
    
    /**
     * Methode qui retourne un Fighter, l'instance créer sera une instance de Bomber , Shooter 
     * ou Gardian en fonction du parametre type. L'instance est obtenue grace au nom , a la couleur et a la strategie du fighter .
     * @param pName nom du fighte
     * @param type caractere representant le type d'instance que l'on souhaite recuperer.
     * @param pColor couleur du fighter
     * @param strat strategie assigné au fighter
     * @return 
     */
    public Fighter createFighter(String pName, char type,String pColor,FighterStrategy strat){
        switch (type){
            case 'G' : return new Gardian(pName,pColor,strat);
            case 'S' : return new Shooter(pName,pColor,strat);
            case 'B' : return new Bomber(pName,pColor,strat);
        }
        return null;
    }
    
  /**
     * Methode qui retourne un Fighter, l'instance créer sera une instance de Bomber , Shooter 
     * ou Gardian en fonction du parametre type. L'instance est obtenue grace au nom , a la couleur, a la strategie du fighter et au dictionnaire de parametre 
     * recuperer via le parseur de fichier.
     * @param pName nom du fighte
     * @param type caractere representant le type d'instance que l'on souhaite recuperer.
     * @param pColor couleur du fighter
     * @param strat strategie assigné au fighter
     * @param pParameter le dictionnaire de parametre recuperer grace au parser de fichier
     * @return 
     */
    public Fighter createFighter(String pName, char type,String pColor, HashMap<String,Integer> pParameter,FighterStrategy strat){
        switch (type){
            case 'G' : return new Gardian(pName,pColor,pParameter,strat);
            case 'S' : return new Shooter(pName,pColor,pParameter,strat);
            case 'B' : return new Bomber(pName,pColor,pParameter,strat);
        }
        return null;
    }
}
