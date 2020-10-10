/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.gamestate;

import models.element.fighters.Fighter;
import models.element.explosives.Explosive;


/**
 * <b>Classe qui permet d'instancier un Proxy, nous permettant d'avoir une vue partiel d'un fighter sur l'etat du jeu.</b>
 * Le proxy est définit par le fighter avec lequel on definit ça visibilité et l'état de jeu qu'il observe.
 */
public class GameStateProxy extends AbstractModel
{
    Fighter fighter;
    
    GameState game;
     /**
     * Constructeur permettant de créer une instance de GameStateProxy en fonction d'un fighter et d'un état de notre modèle de jeu.
     * 
     * @param pFighter  indique à partir de quel combattant on souhaites avoir la visibilité.
     * @param pGameState indique à partir de quel etat de jeu il doit créer sa vue partiel.
     */
    public GameStateProxy(Fighter pFighter,GameState pGameState)
    {
        this.fighter=pFighter;
        
        this.game=pGameState;
    }
    /**
     * Renvoie la grille de jeu contenant uniquement les objets visible par GameStateProxy.fighter.
     * @return la grille contenant uniquement les objets visible par GameStateProxy.fighter. 
     */
    @Override
    public Object[][] getArena()
    {
        int len = this.game.getArena().length;
        
        Object[][] proxyMap = new Object[len][len];
        
        for(int y = 0; y < len; y++)
        {
            for(int x = 0; x < len; x++)
            {
                proxyMap[x][y] = this.game.getArena()[x][y];
            }
        }
        
        for(int y = 0; y < proxyMap.length; y++)
        {
            for(int x = 0; x < proxyMap.length; x++)
            {
               if(proxyMap[x][y] instanceof Explosive)
               {
                   if(this.fighter != ((Explosive)proxyMap[x][y]).getFighter())
                   {
                       proxyMap[x][y] = null;
                   }
               }
            }
        }
        
      
        return proxyMap;
    }
     /**
     * fonction test de la methode GameStateProxy.getArena()
     */
    public void display()
    {
        for(int y = 0; y < this.game.getArena().length; y++) 
            
        {
             for(int x = 0; x < this.game.getArena().length; x++) 
            {
                if(this.game.getArena()[x][y] instanceof Explosive)
                {
                    System.out.println(this.game.getArena()[x][y]+" in "+(x+","+y)); 
                }
                 
            }
        }
    }
}
