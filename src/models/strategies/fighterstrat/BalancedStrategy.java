package models.strategies.fighterstrat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Permet de creer une strategie equilibre
 * @author Erwann
 */
public class BalancedStrategy extends GeneralStrategy {
    
    public BalancedStrategy() {
        super(0.2, 0.2, 0.2, 0.2, 0.2);
    }
    
}
