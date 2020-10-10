package models.strategies.fighterstrat;

import java.util.Random;
import models.utils.Actions;
import models.utils.Direction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe permettant de definir le fonctionnement global des strategies par defaut
 * @author Erwann
 */
public abstract class GeneralStrategy implements FighterStrategy {
 
    protected double move;
    protected double shot;
    protected double shield;
    protected double mine;
    protected double bomb;
            
    /**
     * Le constructeur d'une strategie
     * @param move
     *  le pourcentage de chance qu'un joueur se deplace
     * @param shot
     * le pourcentage de chance qu'un joueur tire
     * @param shield
     * le pourcentage de chance qu'un joueur utilise son bouclier
     * @param bomb
     * le pourcentage de chance qu'un joueur pose une bombe
     * @param mine 
     * le pourcentage de chance qu'un joueur pose une mine
     */
    public GeneralStrategy(double move, double shot, double shield, double bomb, double mine)
    {
        this.move = move;
        this.shot = shot;
        this.shield = shield;
        this.bomb = bomb;
        this.mine = mine;
    }
    
    /**
     * Tirage aleatoire de l'action a realiser selon les ratios de chaque action
     * @return 
     *  Retourne l'action a realiser
     */
    @Override
    public Actions doAction() {
        Random r = new Random();
        double random = 0.0 + r.nextDouble() * (1.0 - 0.0);
        if(random <= this.move)
        {
            return new Actions(Actions.Action.move,Direction.NO_DIR);
        } else if(random <= this.move+this.shot) {
            return new Actions(Actions.Action.shoot,Direction.NO_DIR);
        } else if(random <= this.move+this.shot+this.shield) {
            return new Actions(Actions.Action.shoot,Direction.NO_DIR);
        } else if(random <= this.move+this.shot+this.shield+this.bomb) {
            return new Actions(Actions.Action.mine,Direction.NO_DIR);
        } else {
            return new Actions(Actions.Action.bomb,Direction.NO_DIR);
        } 
    }           
}
