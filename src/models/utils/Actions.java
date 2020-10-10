

package models.utils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe permettant de representer une action ( direction et action)
 * @author Erwann
 */
public class Actions {
    
    /**
     * la lisre d'actions disponibles
     */
    public enum Action {
        shoot,
        move,
        shield,
        mine,
        bomb,
        nothing,
        heal
    }
    
    private Direction dir;
    
    private Action action;
    
    /**
     * Constructeur d'une action
     * @param action
     *  l'action a realiser
     * @param dir 
     *  la direction dans laquelle realiser l'action
     */
    public Actions(Action action,Direction dir)
    {
        this.dir = dir;
        this.action = action;
    }
    
    public Action getAction()
    {
        return this.action;
    }
    
    
}
